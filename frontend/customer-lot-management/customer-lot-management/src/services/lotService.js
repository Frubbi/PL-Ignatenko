import api from './api';

export const lotService = {
  // Получить список лотов с фильтрацией и сортировкой
  getLots: async (params = {}) => {
    const response = await api.get('/lots', { params });
    return response.data;
  },

  // Получить лот по ID
  getLotById: async (id) => {
    const response = await api.get(`/lots/${id}`);
    return response.data;
  },

  // Создать новый лот
  createLot: async (lotData) => {
    const response = await api.post('/lots', lotData);
    return response.data;
  },

  // Обновить лот
  updateLot: async (id, lotData) => {
    const response = await api.put(`/lots/${id}`, lotData);
    return response.data;
  },

  // Удалить лот
  deleteLot: async (id) => {
    const response = await api.delete(`/lots/${id}`);
    return response.data;
  }
};