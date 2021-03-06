import './enzyme.config.js'                   // (1)
import React from 'react'
import { mount , shallow} from 'enzyme'              // (2)
import ChooseFile from '../src/components/Application/ChooseFile'
import {Button} from "reactstrap";


const testProps = {
    trip: {
        type: "trip",
        version: "3",
        title: "Stuffity",
        options: {
            units: "miles",
            unitName: "miles",
            unitRadius: "3959",
            optimization: "none"
        },
        places: [],
        distances: [],
        map: ''
    },
    config: {
        filters: []
    }
};

function updateTFFI(tffi) {
    testProps.trip = tffi;
}


function testRender() {
    const fileWrapper = mount((
        <ChooseFile
            trip={testProps.trip}
            updateTFFI={updateTFFI}
            config={testProps.config}
        />
    ));

    let actualButtonTexts = fileWrapper.find('Button').map((button)=>{
        return button.text();
    });

    let expectedButtonTexts = [];
    //let expectedButtonTexts = ['Search for locations', 'Filter Your Search', 'Search', 'Add destination to Trip', 'Add all destinations to Trip'];
    expect(actualButtonTexts).toEqual(expectedButtonTexts)
}

test('Testing plan function in Trip.js', testRender);