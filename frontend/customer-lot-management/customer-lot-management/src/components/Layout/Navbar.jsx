import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => {
  const location = useLocation();

  const isActive = (path) => {
    return location.pathname === path || location.pathname.startsWith(path + '/');
  };

  return (
    <nav className="navbar">
      <div className="navbar-brand">
        <h2>Управление контрагентами и лотами</h2>
      </div>
      <div className="navbar-menu">
        <Link 
          to="/customers" 
          className={`nav-link ${isActive('/customers') ? 'active' : ''}`}
        >
          Контрагенты
        </Link>
        <Link 
          to="/lots" 
          className={`nav-link ${isActive('/lots') ? 'active' : ''}`}
        >
          Лоты
        </Link>
      </div>
    </nav>
  );
};

export default Navbar;