import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { customerService } from '../../services/customerService';
import SearchBar from '../common/SearchBar';
import LoadingSpinner from '../common/LoadingSpinner';
import ErrorAlert from '../common/ErrorAlert';
import './CustomerList.css';

const CustomerList = () => {
  const [customers, setCustomers] = useState([]);
  const [filteredCustomers, setFilteredCustomers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [sortField, setSortField] = useState('customer_name');
  const [sortOrder, setSortOrder] = useState('asc');
  const [filters, setFilters] = useState({
    customer_name: '',
    customer_inn: '',
    customer_code: ''
  });
  
  const navigate = useNavigate();

  useEffect(() => {
    fetchCustomers();
  }, []);

  useEffect(() => {
    applyFiltersAndSorting();
  }, [customers, filters, sortField, sortOrder]);

  const fetchCustomers = async () => {
    try {
      setLoading(true);
      setError(null);
      const data = await customerService.getCustomers();
      setCustomers(data);
    } catch (err) {
      setError('Ошибка при загрузке контрагентов');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const applyFiltersAndSorting = () => {
    let result = [...customers];

    // Применение фильтров
    if (filters.customer_name) {
      result = result.filter(customer =>
        customer.customer_name.toLowerCase().includes(filters.customer_name.toLowerCase())
      );
    }

    if (filters.customer_inn) {
      result = result.filter(customer =>
        customer.customer_inn.includes(filters.customer_inn)
      );
    }

    if (filters.customer_code) {
      result = result.filter(customer =>
        customer.customer_code.includes(filters.customer_code)
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

    setFilteredCustomers(result);
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
    if (window.confirm('Вы уверены, что хотите удалить этого контрагента?')) {
      try {
        await customerService.deleteCustomer(id);
        fetchCustomers();
      } catch (err) {
        setError('Ошибка при удалении контрагента');
      }
    }
  };

  const renderSortArrow = (field) => {
    if (sortField !== field) return null;
    return sortOrder === 'asc' ? ' ↑' : ' ↓';
  };

  if (loading) return <LoadingSpinner />;

  return (
    <div className="customer-list">
      <div className="header">
        <h2>Справочник контрагентов</h2>
        <button 
          className="btn btn-primary"
          onClick={() => navigate('/customers/new')}
        >
          Добавить контрагента
        </button>
      </div>

      {error && <ErrorAlert message={error} />}

      <div className="filters">
        <SearchBar
          placeholder="Поиск по наименованию"
          value={filters.customer_name}
          onChange={(value) => handleFilterChange('customer_name', value)}
        />
        <input
          type="text"
          placeholder="Фильтр по ИНН"
          value={filters.customer_inn}
          onChange={(e) => handleFilterChange('customer_inn', e.target.value)}
          className="filter-input"
        />
        <input
          type="text"
          placeholder="Фильтр по коду"
          value={filters.customer_code}
          onChange={(e) => handleFilterChange('customer_code', e.target.value)}
          className="filter-input"
        />
      </div>

      <div className="table-container">
        <table className="data-table">
          <thead>
            <tr>
              <th onClick={() => handleSort('customer_code')}>
                Код {renderSortArrow('customer_code')}
              </th>
              <th onClick={() => handleSort('customer_name')}>
                Наименование {renderSortArrow('customer_name')}
              </th>
              <th onClick={() => handleSort('customer_inn')}>
                ИНН {renderSortArrow('customer_inn')}
              </th>
              <th>КПП</th>
              <th>Тип</th>
              <th>Действия</th>
            </tr>
          </thead>
          <tbody>
            {filteredCustomers.map((customer) => (
              <tr key={customer.id}>
                <td>{customer.customer_code}</td>
                <td>{customer.customer_name}</td>
                <td>{customer.customer_inn}</td>
                <td>{customer.customer_kpp}</td>
                <td>
                  {customer.is_organization && 'Юр. лицо'}
                  {customer.is_person && 'Физ. лицо'}
                </td>
                <td>
                  <div className="action-buttons">
                    <button
                      className="btn btn-sm btn-info"
                      onClick={() => navigate(`/customers/${customer.id}`)}
                    >
                      Просмотр
                    </button>
                    <button
                      className="btn btn-sm btn-warning"
                      onClick={() => navigate(`/customers/${customer.id}/edit`)}
                    >
                      Изменить
                    </button>
                    <button
                      className="btn btn-sm btn-danger"
                      onClick={() => handleDelete(customer.id)}
                    >
                      Удалить
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        {filteredCustomers.length === 0 && (
          <div className="no-data">Данные не найдены</div>
        )}
      </div>
    </div>
  );
};

export default CustomerList;