import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import EventDetail from './components/EventDetail';
import EventList from './components/EventList';
import Home from './components/Home';
import Profile from './components/Profile';
import TransactionList from './components/TransactionList';
import { Layout } from 'antd';
import SideMenu from './components/common/SideMenu';
import Login from './components/Login';
import { Redirect } from './components/Redirect';

const { Content, Footer, Sider } = Layout;

function App() {
  return (
    <BrowserRouter>
      <div id = "container">
        <Layout hasSider={true} style={{height: "100vh"}}>
          <Sider
            breakpoint="lg"
            collapsedWidth="0">
              <SideMenu />
          </Sider>
          <Layout>
            <Content style={{ margin: '24px 16px 0' }}>
              <div className="site-layout-background" style={{ padding: 28}}>
              <Routes>
                <Route path = "/profile" element={<Profile />} />
                <Route path = "/events" element={<EventList />} />
                <Route path = "/event/:id" element={<EventDetail />} />
                <Route path = "/transactions" element={<TransactionList />} />
                <Route path = "/" element={<Home />} />
                <Route path = "/login" element={<Login />} />
                <Route path = "/oauth2/authorization/google" element={<Redirect />} />
              </Routes>
              </div>
            </Content>
              <Footer style={{ textAlign: 'center' }}>@ 2021 Ticket App.</Footer>
          </Layout>
        </Layout>
      </div>
    </BrowserRouter>
  );
}

export default App;
