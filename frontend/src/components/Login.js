import { Button, Typography } from 'antd';
import * as React from 'react';
import { BASE_URL } from '../constants/Constant';

const Login = () => {
    return (
        <div>
            <Typography.Title level={3}>Welcome to Ticket App!</Typography.Title>
            <Typography.Paragraph>Please sign in first.</Typography.Paragraph>
            <Button 
                type="primary" 
                href={`${BASE_URL}/oauth2/authorization/google`}
            >
                Login with Google
            </Button>
        </div>)

}
export default Login;