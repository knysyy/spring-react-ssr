import React from 'react'
import ReactDOM from 'react-dom'
import ReactDOMServer from 'react-dom/server'

const anyWindow = window;

export const initialize = (Root) => {
    anyWindow.renderApp = () => {
        ReactDOM.hydrate(<Root />, document.getElementById('root'));
    }
    anyWindow.renderAppOnServer = () => {
        return ReactDOMServer.renderToString(<Root />);
    }
}
