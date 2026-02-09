import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { lotService } from '../../services/lotService';
import SearchBar from '../common/SearchBar';
import LoadingSpinner from '../common/LoadingSpinner';
import ErrorAlert from '../common/ErrorAlert';
import './LotList.css';

const LotList = () => {
  const [lots, setLots] = useState([]);
  const [filteredLots, setFilteredLots] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [sortField, setSortField] = useState('lot_name');
  const [sortOrder, setSortOrder] = useState('asc');
  const [filters, setFilters] = useState({
    lot_name: '',
    customer_code: '',
    currency_code: ''
  });
  
  const navigate = useNavigate();

  useEffect(() => {
    fetchLots();
  }, []);

  useEffect(() => {
    applyFiltersAndSorting();
  }, [lots, filters, sortField, sortOrder]);

  const fetchLots = async () => {
    try {
      setLoading(true);
      setError(null);
      const data = await lotService.getLots();
      setLots(data);
    } catch (err) {
      setError('Ошибка при загрузке лотов');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const applyFiltersAndSorting = () => {
    let result = [...lots];

    // Применение фильтров
    if (filters.lot_name) {
      result = result.filter(lot =>
        lot.lot_name.toLowerCase().includes(filters.lot_name.toLowerCase())
      );
    }

    if (filters.customer_code) {
      result = result.filter(lot =>
        lot.customer_code.includes(filters.customer_code)
      );
    }

    if (filters.currency_code) {
      result = result.filter(lot =>
        lot.currency_code === filters.currency_code
      );
    }

    // Сортировка
    result.sort((a, b) => {
      const aValue = a[sortField];
      const bValue = b[sortField];
      
      if (sortOrder === 'asc') {
        return aValue < bValue ? -1 : aValue > bValue ? 1 : 0;
      } else {
        return bValue < aValue ? -1 : bValue > aValue ? 1 : 0;
      }
    });

    setFilteredLots(result);
  };

  const handleSort = (field) => {
    if (sortField === field) {
      setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    } else {
      setSortField(field);
      setSortOrder('asc');
    }
  };

  const handleFilterChange = (field, value) => {
    setFilters(prev => ({
      ...prev,
      [field]: value
    }));
  };

  const handleDelete = async (id) => {
    if (window.confirm('Вы уверены, что хотите удалить этот лот?')) {
      try {
        await lotService.deleteLot(id);
        fetchLots();
      } catch (err) {
        setError('Ошибка при удалении лота');
      }
    }
  };

  const renderSortArrow = (field) => {
    if (sortField !== field) return null;
    return sortOrder === 'asc' ? ' ↑' : ' ↓';
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('ru-RU');
  };

  if (loading) return <LoadingSpinner />;

  return (
    <div className="lot-list">
      <div className="header">
        <h2>Лоты</h2>
        <button 
          className="btn btn-primary"
          onClick={() => navigate('/lots/new')}
        >
          Добавить лот
        </button>
      </div>

      {error && <ErrorAlert message={error} />}

      <div className="filters">
        <SearchBar
          placeholder="Поиск по наименованию лота"
          value={filters.lot_name}
          onChange={(value) => handleFilterChange('lot_name', value)}
        />
        <input
          type="text"
          placeholder="Фильтр по коду контрагента"
          value={filters.customer_code}
          onChange={(e) => handleFilterChange('customer_code', e.target.value)}
          className="filter-input"
        />
        <select
          value={filters.currency_code}
          onChange={(e) => handleFilterChange('currency_code', e.target.value)}
          className="filter-select"
        >
          <option value="">Все валюты</option>
          <option value="RUB">RUB</option>
          <option value="USD">USD</option>
          <option value="EUR">EUR</option>
        </select>
      </div>

      <div className="table-container">
        <table className="data-table">
          <thead>
            <tr>
              <th onClick={() => handleSort('lot_name')}>
                Наименование лота {renderSortArrow('lot_name')}
              </th>
              <th>Контрагент</th>
              <th onClick={() => handleSort('price')}>
                Стоимость {renderSortArrow('price')}
              </th>
              <th>Валюта</th>
              <th>НДС</th>
              <th onClick={() => handleSort('date_delivery')}>
                Дата доставки {renderSortArrow('date_delivery')}
              </th>
              <th>Действия</th>
            </tr>
          </thead>
          <tbody>
            {filteredLots.map((lot) => (
              <tr key={lot.id}>
                <td>{lot.lot_name}</td>
                <td>{lot.customer_name} ({lot.customer_code})</td>
                <td>{lot.price.toLocaleString('ru-RU')}</td>
                <td>{lot.currency_code}</td>
                <td>{lot.nds_rate}</td>
                <td>{formatDate(lot.date_delivery)}</td>
                <td>
                  <div className="action-buttons">
                    <button
                      className="btn btn-sm btn-info"
                      onClick={() => navigate(`/lots/${lot.id}`)}
                    >
                      Просмотр
                    </button>
                    <button
                      className="btn btn-sm btn-warning"
                      onClick={() => navigate(`/lots/${lot.id}/edit`)}
                    >
                      Изменить
                    </button>
                    <button
                      className="btn btn-sm btn-danger"
                      onClick={() => handleDelete(lot.id)}
                    >
                      Удалить
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {filteredLots.length === 0 && (
          <div className="no-data">Данные не найдены</div>
        )}
      </div>
    </div>
  );
};

export default LotList;