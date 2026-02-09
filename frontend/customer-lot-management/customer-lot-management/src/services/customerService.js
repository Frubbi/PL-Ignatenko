import api from './api';

export const customerService = {
  // Получить список контрагентов с фильтрацией и сортировкой
  getCustomers: async (params = {}) => {
    const response = await api.get('/customers', { params });
    return response.data;
  },

  // Получить контрагента по ID
  getCustomerById: async (id) => {
    const response = await api.get(`/customers/${id}`);
    return response.data;
  },

  // Создать нового контрагента
  createCustomer: async (customerData) => {
    const response = await api.post('/customers', customerData);
    return response.data;
  },

  // Обновить контрагента
  updateCustomer: async (id, customerData) => {
    const response = await api.put(`/customers/${id}`, customerData);
    return response.data;
  },

  // Удалить контрагента
  deleteCustomer: async (id) => {
    const response = await api.delete(`/customers/${id}`);
    return response.data;
  },

  // Получить список контрагентов для выпадающего списка
  getCustomersForDropdown: async () => {
    const response = await api.get('/customers/dropdown');
    return response.data;
  }
};