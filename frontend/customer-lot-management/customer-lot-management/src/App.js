import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import CustomerList from './components/Customers/CustomerList';
import CustomerForm from './components/Customers/CustomerForm';
import CustomerDetail from './components/Customers/CustomerDetail';
import LotList from './components/Lots/LotList';
import LotForm from './components/Lots/LotForm';
import LotDetail from './components/Lots/LotDetail';

function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          {/* Customer routes */}
          <Route path="/" element={<CustomerList />} />
          <Route path="/customers" element={<CustomerList />} />
          <Route path="/customers/new" element={<CustomerForm />} />
          <Route path="/customers/:id/edit" element={<CustomerForm />} />
          <Route path="/customers/:id" element={<CustomerDetail />} />
          
          {/* Lot routes */}
          <Route path="/lots" element={<LotList />} />
          <Route path="/lots/new" element={<LotForm />} />
          <Route path="/lots/:id/edit" element={<LotForm />} />
          <Route path="/lots/:id" element={<LotDetail />} />
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;