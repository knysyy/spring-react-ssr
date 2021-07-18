import React, {useState} from 'react'
import {onServer} from "../on-server";
import {initialize} from "../init";
import Navigation from "../components/Navigation";

const List = () => {
    const [initialList, initScript] = onServer(api => api.getList(), [], 'app.list');
    const [list, setList] = useState(initialList)
    const [newItem, setNewItem] = useState('')

    const fetchList = async () => {
        const response = await window.fetch('/api/list')
        setList(await response.json())
    }

    const addNewItem = async () => {
        await (window.fetch('/api/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({content: newItem})
        }))
        await fetchList()
        setNewItem('')
    }

    return (
        <div>
            <Navigation/>
            <h1>List</h1>
            <ul>{list.map(i => <li key={i.id}>{i.content}</li>)}</ul>
            <div>
                <input type="text" value={newItem} onChange={e => setNewItem(e.target.value)}/>
                <button onClick={addNewItem}>Add</button>
            </div>
            {initScript}
        </div>
    )
}

initialize(List);
