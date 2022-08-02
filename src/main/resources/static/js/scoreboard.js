function createScoreboard(scoreboard) {
    console.log(scoreboard);

    for (let busLine of scoreboard.busLines) {
        // create busLine button
        console.log(busLine.designation);
        const busLineButton = document.createElement('button');
        busLineButton.classList.add('collapsible');
        busLineButton.textContent = 'Line: ' + busLine.designation + ' Direction: ' + busLine.direction;


        // add busLine button to parent div
        document.getElementById('bus_lines').appendChild(busLineButton);


        // create div for stop list
        const stopList = document.createElement('div');
        stopList.classList.add('content');


        // populate stop list
        for (let stopName of busLine.stopNames) {
            console.log("StopName: " + stopName);

            const stopItem = document.createElement('p');
            stopItem.textContent = stopName;
            stopList.appendChild(stopItem);
        }


        document.getElementById('bus_lines').appendChild(stopList);
    }
}