import * as React from 'react';
import 'antd/dist/antd.css';
import { Menu } from 'antd';
import { Link, useLocation } from 'react-router-dom';
import { TransactionOutlined, UserOutlined, ShopOutlined } from '@ant-design/icons';


const SideMenu = (props) => {  
    const location = useLocation();  

    console.log(location.pathname);

    return (    
        <div>
            <div style={{display: "flex"}}>
                <div className="logo" />
                {props.bpReached && <div className="logo-text" >Ticket App </div>}
            </div>
            <Menu selectedKeys={location.pathname} theme="dark" mode="inline">
                <Menu.Item key="/profile" icon={<UserOutlined />}>
                    <Link to='/profile'>Profile</Link>
                </Menu.Item>
                <Menu.Item key="/events" icon={<ShopOutlined />}>
                    <Link to='/events'>Events</Link>
                </Menu.Item>
                <Menu.Item key="/transactions" icon={<TransactionOutlined />}>
                    <Link to='/transactions'>Transactions</Link>
                </Menu.Item>
            </Menu>
        </div>  
    );

};

export default SideMenu;