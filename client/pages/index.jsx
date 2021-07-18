import React from 'react'
import {initialize} from "../init";
import Navigation from "../components/Navigation";

const Index = () => {
    return (
        <div>
            <Navigation/>
            <h1>Index</h1>
        </div>
    )
};

initialize(Index);
