import { List } from 'antd';
import * as React from 'react';
import { Link } from 'react-router-dom';

const data = [
    'Racing car sprays burning fuel into crowd.',
    'Japanese princess to wed commoner.',
    'Australian walks 100km after outback crash.',
    'Man charged over missing wedding girl.',
    'Los Angeles battles huge wildfires.',
  ];

const EventList = () => {
    return (
        <List 
            dataSource={data}
            renderItem={(item) => (
                <List.Item
                    actions={[<Link to={`/event/${item}`}>view detail</Link>]}
                >
                    <List.Item.Meta
                        title={<p>Title</p>}
                        description={item}/>
                </List.Item>
            )}
        />
    );
}
export default EventList;