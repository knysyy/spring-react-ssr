import React from 'react'

export function onServer(callback, defaultValue, valueIdentifier) {
    const anyWindow = window
    if (anyWindow.isServer) {
        const jsonValue = callback(anyWindow.api);
        const sanitizedJson = jsonValue
            .replace(/\\/g, '\\\\')
            .replace(/"/g, '\\"')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
        const scriptContent = `
            <script>
            if(!window.serverData) { window.serverData = {} }
            window.serverData['${valueIdentifier}'] = JSON.parse("${sanitizedJson}".replace(/&lt;/g, '<').replace(/&gt;/g, '>'))
            </script>
        `
        const initScript = <div dangerouslySetInnerHTML={{__html: scriptContent}}></div>
        return [JSON.parse(jsonValue), initScript]
    }
    if(anyWindow.serverData) {
        const value = anyWindow.serverData[valueIdentifier]
        anyWindow.serverData[valueIdentifier] = undefined
        if (value) {
            return [value, undefined];
        }
    }
    return [defaultValue, undefined]
}
