function createScoreboard(scoreboard) {
    console.log(scoreboard);

    for (let i of scoreboard.busLines) {
        // create busLine button
        console.log(i.designation);
        const busLineButton = document.createElement('button');
        busLineButton.classList.add('collapsible');
        busLineButton.textContent = 'Line: ' + i.designation + ' Direction: ' + i.direction;


        // add busLine button to parent div
        document.getElementById('bus_lines').appendChild(busLineButton);


        // create div for stop list
        const stopList = document.createElement('div');
        stopList.classList.add('content');


        // populate stop list
        i.stopNames.forEach(({stopName}) => {
            const stopItem = document.createElement('p');
            stopItem.textContent = stopName;
            stopList.appendChild(stopItem);
        })

        document.getElementById('bus_lines').appendChild(stopList);
    }
}