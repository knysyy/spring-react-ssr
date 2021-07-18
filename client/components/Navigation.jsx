import React from "react"

const Navigation = () => {
    return (
        <ul style={{ listStyle: 'none', display: 'flex' }}>
            <li style={{ marginRight: '5px' }}>
                <a href="/">Index</a>
            </li>
            <li>
                <a href="/list">List</a>
            </li>
        </ul>
    );
};

export default Navigation;
