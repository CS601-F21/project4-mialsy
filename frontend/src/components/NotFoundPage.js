import { Typography } from "antd";
import * as React from 'react';

const NotFoundPage = () => (
    <div>
        <Typography.Title level={1}>
            404 Not Found.
        </Typography.Title>
        <Typography.Paragraph>
            Sorry we cannot find the page you requested.
        </Typography.Paragraph>
    </div>
);

export default NotFoundPage;