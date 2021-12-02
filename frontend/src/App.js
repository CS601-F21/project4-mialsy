import './App.less';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import EventDetail from './components/EventDetail';
import EventList from './components/EventList';
import Home from './components/Home';
import Profile from './components/Profile';
import TransactionList from './components/TransactionList';
import { Layout } from 'antd';
import SideMenu from './components/common/SideMenu';
import Login from './components/Login';
import Redirect from './components/Redirect';
import { CookiesProvider, useCookies } from 'react-cookie';
import { isAuth } from './utils/AuthUtil';

const { Content, Footer, Sider } = Layout;

function App() {

  const [cookie] = useCookies('name');

  return (
    <CookiesProvider>
    <BrowserRouter>
      <div id = "container">
        <Layout hasSider={true} >
          <Sider
            breakpoint="lg"
            collapsedWidth="0"
            style={{
              overflow: 'auto',
              height: '100vh',
              position: 'fixed',
              left: 0,
            }}            
            >
              <SideMenu />
          </Sider>
          <Layout style={{ marginLeft: 200 }}>
            <Content style={{ margin: '24px 16px 0' }}>
              <div className="site-layout-background" style={{ padding: 28, minHeight: "100vh"}}>
                { isAuth(cookie) ? 
                    <Routes>
                      <Route path = "/profile" element={<Profile />} />
                      <Route path = "/events" element={<EventList />} />
                      <Route path = "/event/:id" element={<EventDetail />} />
                      <Route path = "/transactions" element={<TransactionList />} />
                      <Route path = "/" element={<Home />} />
                    </Routes> 
                    :
                    <Login />
                }
              </div>
            </Content>
              <Footer style={{ textAlign: 'center' }}>@ 2021 Ticket App.</Footer>
          </Layout>
        </Layout>
      </div>
    </BrowserRouter>
    </CookiesProvider>
  );
}

export default App;
