import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { customerService } from '../../services/customerService';
import LoadingSpinner from '../common/LoadingSpinner';
import ErrorAlert from '../common/ErrorAlert';
import './CustomerForm.css';

const CustomerForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const isEditMode = !!id;
  
  const [loading, setLoading] = useState(false);
  const [loadingCustomers, setLoadingCustomers] = useState(false);
  const [error, setError] = useState(null);
  const [customers, setCustomers] = useState([]);
  
  const [formData, setFormData] = useState({
    customer_code: '',
    customer_name: '',
    customer_inn: '',
    customer_kpp: '',
    customer_legal_address: '',
    customer_postal_address: '',
    customer_email: '',
    customer_code_main: '',
    is_organization: false,
    is_person: false
  });

  useEffect(() => {
    fetchCustomersForDropdown();
    
    if (isEditMode) {
      fetchCustomer();
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

  const fetchCustomer = async () => {
    try {
      setLoading(true);
      const data = await customerService.getCustomerById(id);
      setFormData(data);
    } catch (err) {
      setError('Ошибка при загрузке данных контрагента');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      if (isEditMode) {
        await customerService.updateCustomer(id, formData);
      } else {
        await customerService.createCustomer(formData);
      }
      navigate('/customers');
    } catch (err) {
      setError(err.response?.data?.message || 'Ошибка при сохранении');
    } finally {
      setLoading(false);
    }
  };

  const handleCustomerTypeChange = (type) => {
    setFormData(prev => ({
      ...prev,
      is_organization: type === 'organization',
      is_person: type === 'person'
    }));
  };

  if (loading && isEditMode) return <LoadingSpinner />;

  return (
    <div className="customer-form">
      <h2>{isEditMode ? 'Редактирование контрагента' : 'Добавление контрагента'}</h2>
      
      {error && <ErrorAlert message={error} />}
      
      <form onSubmit={handleSubmit}>
        <div className="form-grid">
          <div className="form-group">
            <label>Код контрагента *</label>
            <input
              type="text"
              name="customer_code"
              value={formData.customer_code}
              onChange={handleChange}
              required
              disabled={isEditMode}
            />
          </div>

          <div className="form-group">
            <label>Наименование *</label>
            <input
              type="text"
              name="customer_name"
              value={formData.customer_name}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label>ИНН *</label>
            <input
              type="text"
              name="customer_inn"
              value={formData.customer_inn}
              onChange={handleChange}
              required
              pattern="[0-9]{10,12}"
              title="ИНН должен содержать 10 или 12 цифр"
            />
          </div>

          <div className="form-group">
            <label>КПП</label>
            <input
              type="text"
              name="customer_kpp"
              value={formData.customer_kpp}
              onChange={handleChange}
              pattern="[0-9]{9}"
              title="КПП должен содержать 9 цифр"
            />
          </div>

          <div className="form-group full-width">
            <label>Юридический адрес</label>
            <textarea
              name="customer_legal_address"
              value={formData.customer_legal_address}
              onChange={handleChange}
              rows="3"
            />
          </div>

          <div className="form-group full-width">
            <label>Почтовый адрес</label>
            <textarea
              name="customer_postal_address"
              value={formData.customer_postal_address}
              onChange={handleChange}
              rows="3"
            />
          </div>

          <div className="form-group">
            <label>Электронная почта</label>
            <input
              type="email"
              name="customer_email"
              value={formData.customer_email}
              onChange={handleChange}
            />
          </div>

          <div className="form-group">
            <label>Вышестоящий контрагент</label>
            <select
              name="customer_code_main"
              value={formData.customer_code_main}
              onChange={handleChange}
              disabled={loadingCustomers}
            >
              <option value="">Не выбран</option>
              {customers.map(customer => (
                <option key={customer.id} value={customer.customer_code}>
                  {customer.customer_name} ({customer.customer_code})
                </option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label>Тип контрагента *</label>
            <div className="radio-group">
              <label className="radio-label">
                <input
                  type="radio"
                  name="customer_type"
                  checked={formData.is_organization}
                  onChange={() => handleCustomerTypeChange('organization')}
                  required
                />
                Юридическое лицо
              </label>
              <label className="radio-label">
                <input
                  type="radio"
                  name="customer_type"
                  checked={formData.is_person}
                  onChange={() => handleCustomerTypeChange('person')}
                  required
                />
                Физическое лицо
              </label>
            </div>
          </div>
        </div>

        <div className="form-actions">
          <button
            type="button"
            className="btn btn-secondary"
            onClick={() => navigate('/customers')}
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

export default CustomerForm;