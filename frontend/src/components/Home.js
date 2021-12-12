import { Carousel, Typography } from "antd";
import * as React from "react";

/**
 * style for cards in carosusel
 */
const cardStyle = {
  height: "80vh",
  lineHeight: "160px",
  background: "linear-gradient(#303956, 90%, #B48B9B)",
  textAligh: "center",
};

/**
 * Welcome messages to display
 */
const welcomeMessages = [
  "Here you can explore events, ",
  "create your own events, ",
  "buy a ticket for an event,",
  "and transfer a ticket to your friends.",
  "Enjoy!",
];

/**
 * Home Page component when user has logged in
 * 
 * @returns Home component 
 */
const Home = () => {
  return (
    <div>
      <Typography.Title level={3}>Welcome to Ticket App!</Typography.Title>
      <Carousel autoplay>
        {welcomeMessages.map((msg) => (
          <div>
            <div style={cardStyle}>
              <Typography.Title
                level={1}
                style={{
                  color: "white",
                  position: "absolute",
                  marginLeft: 150,
                  top: "35%",
                }}
              >
                {msg}
              </Typography.Title>
            </div>
          </div>
        ))}
      </Carousel>
    </div>
  );
};
export default Home;
