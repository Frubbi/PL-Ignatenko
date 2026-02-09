import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { lotService } from '../../services/lotService';
import { customerService } from '../../services/customerService';
import LoadingSpinner from '../common/LoadingSpinner';
import ErrorAlert from '../common/ErrorAlert';
import './LotForm.css';

const LotForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const isEditMode = !!id;
  
  const [loading, setLoading] = useState(false);
  const [loadingCustomers, setLoadingCustomers] = useState(false);
  const [error, setError] = useState(null);
  const [customers, setCustomers] = useState([]);
  
  const [formData, setFormData] = useState({
    lot_name: '',
    customer_code: '',
    price: '',
    currency_code: 'RUB',
    nds_rate: '20%',
    place_delivery: '',
    date_delivery: ''
  });

  const currencyOptions = [
    { value: 'RUB', label: 'RUB - Российский рубль' },
    { value: 'USD', label: 'USD - Доллар США' },
    { value: 'EUR', label: 'EUR - Евро' }
  ];

  const ndsOptions = [
    { value: 'Без НДС', label: 'Без НДС' },
    { value: '18%', label: '18%' },
    { value: '20%', label: '20%' }
  ];

  useEffect(() => {
    fetchCustomersForDropdown();
    
    if (isEditMode) {
      fetchLot();
    }
  }, [id]);

  const fetchCustomersForDropdown = async () => {
    try {
      setLoadingCustomers(true);
      const data = await customerService.getCustomersForDropdown();
      setCustomers(data);
    } catch (err) {
      console.error('Ошибка при загрузке списка контрагентов:', err);
    } finally {
      setLoadingCustomers(false);
    }
  };

  const fetchLot = async () => {
    try {
      setLoading(true);
      const data = await lotService.getLotById(id);
      
      // Форматирование даты для input[type="datetime-local"]
      const formattedData = {
        ...data,
        date_delivery: data.date_delivery ? 
          new Date(data.date_delivery).toISOString().slice(0, 16) : ''
      };
      
      setFormData(formattedData);
    } catch (err) {
      setError('Ошибка при загрузке данных лота');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      if (isEditMode) {
        await lotService.updateLot(id, formData);
      } else {
        await lotService.createLot(formData);
      }
      navigate('/lots');
    } catch (err) {
      setError(err.response?.data?.message || 'Ошибка при сохранении');
    } finally {
      setLoading(false);
    }
  };

  if (loading && isEditMode) return <LoadingSpinner />;

  return (
    <div className="lot-form">
      <h2>{isEditMode ? 'Редактирование лота' : 'Добавление лота'}</h2>
      
      {error && <ErrorAlert message={error} />}
      
      <form onSubmit={handleSubmit}>
        <div className="form-grid">
          <div className="form-group full-width">
            <label>Наименование лота *</label>
            <input
              type="text"
              name="lot_name"
              value={formData.lot_name}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>Контрагент *</label>
            <select
              name="customer_code"
              value={formData.customer_code}
              onChange={handleChange}
              required
              disabled={loadingCustomers}
            >
              <option value="">Выберите контрагента</option>
              {customers.map(customer => (
                <option key={customer.id} value={customer.customer_code}>
                  {customer.customer_name} ({customer.customer_code})
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Начальная стоимость *</label>
            <input
              type="number"
              name="price"
              value={formData.price}
              onChange={handleChange}
              required
              min="0"
              step="0.01"
            />
          </div>

          <div className="form-group">
            <label>Валюта *</label>
            <select
              name="currency_code"
              value={formData.currency_code}
              onChange={handleChange}
              required
            >
              {currencyOptions.map(option => (
                <option key={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Ставка НДС *</label>
            <select
              name="nds_rate"
              value={formData.nds_rate}
              onChange={handleChange}
              required
            >
              {ndsOptions.map(option => (
                <option key={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
          </div>

          <div className="form-group full-width">
            <label>Грузополучатель</label>
            <textarea
              name="place_delivery"
              value={formData.place_delivery}
              onChange={handleChange}
              rows="3"
            />
          </div>

          <div className="form-group">
            <label>Дата доставки *</label>
            <input
              type="datetime-local"
              name="date_delivery"
              value={formData.date_delivery}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <div className="form-actions">
          <button
            type="button"
            className="btn btn-secondary"
            onClick={() => navigate('/lots')}
            disabled={loading}
          >
            Отмена
          </button>
          <button
            type="submit"
            className="btn btn-primary"
            disabled={loading}
          >
            {loading ? 'Сохранение...' : (isEditMode ? 'Обновить' : 'Создать')}
          </button>
        </div>
      </form>
    </div>
  );
};

export default LotForm;