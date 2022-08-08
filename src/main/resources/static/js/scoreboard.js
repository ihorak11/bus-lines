function createScoreboard(scoreboard) {
    //get main accordion div
    const accordionContainer = document.getElementById('accordion-container');

    for (let busLine of scoreboard.busLines) {
        const accordion = document.createElement("div");
        accordion.classList.add('ac');
        accordionContainer.appendChild(accordion);

        const busLineHeader = document.createElement("h2");
        busLineHeader.classList.add('ac-header');

        const busLineButton = document.createElement('button');
        busLineButton.type = 'button';
        busLineButton.classList.add('ac-trigger');
        busLineButton.textContent = 'Line: ' + busLine.designation + ' Direction: ' + busLine.direction + ' Number of stops: ' + busLine.noOfStops;
        busLineButton.style.textAlign = 'center';

        accordion.appendChild(busLineHeader);
        busLineHeader.appendChild(busLineButton);

        // create panel div
        const stopListPanel = document.createElement('div');
        stopListPanel.classList.add('ac-panel');
        stopListPanel.style.textAlign = 'center';

        // populate stop names
        for (let stopName of busLine.stopNames) {

            const stopItem = document.createElement('p');

            stopItem.classList.add('ac-text');
            stopItem.textContent = stopName;

            stopListPanel.appendChild(stopItem);
        }

        //add stop panel to accordion
        accordion.appendChild(stopListPanel)
    }
}