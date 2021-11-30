import * as React from 'react';
import 'antd/dist/antd.css';
import { Menu } from 'antd';
import { Link } from 'react-router-dom';
import { TransactionOutlined, UserOutlined, ShopOutlined } from '@ant-design/icons';


const SideMenu = () => (
    <div>
        <div className="logo" />
        <Menu theme="dark" mode="inline" defaultSelectedKeys={['4']}>
            <Menu.Item key="1" icon={<UserOutlined />}>
                <Link to='/profile'>Profile</Link>
            </Menu.Item>
            <Menu.Item key="2" icon={<ShopOutlined />}>
                <Link to='/events'>Events</Link>
            </Menu.Item>
            <Menu.Item key="3" icon={<TransactionOutlined />}>
                <Link to='/transactions'>Transactions</Link>
            </Menu.Item>
        </Menu>
    </div>
);

export default SideMenu;