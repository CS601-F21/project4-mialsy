
import { Input, Typography } from 'antd';
import React from 'react'; 

const {Text} = Typography;

const TransferModalContent = (props) => {
    return (
        <div>
            Transfer ticket of the event - <Text strong> {props.item} </Text>
            <div style={{display: 'flex', flex: 'auto', marginTop: 20}}>
                <Text style={{margin: 10}}> To user: </Text>  
                <Input placeholder="Input github username" 
                    allowClear 
                    style={{maxWidth: 300}}
                    value={props.user}
                    onChange={(e)=> props.setToUser(e.target.value)}
                    />
            </div>
        </div>      
    );
}
export default TransferModalContent;