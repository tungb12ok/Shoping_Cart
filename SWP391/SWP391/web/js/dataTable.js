let currentSortColumnIndex = -1;
let sortAscending = true;

document.addEventListener("DOMContentLoaded", function () {
    let table = document.getElementById("userTable");
    let ths = table.querySelectorAll("th[data-sort]");
    ths.forEach(function (th, index) {
        th.addEventListener("click", function () {
            if (currentSortColumnIndex === index) {
                sortAscending = !sortAscending;
            } else {
                sortAscending = true;
                currentSortColumnIndex = index;
            }
            sortTable(table, index, th.getAttribute("data-sort"), sortAscending);
            updateSortIndicator(th);
        });
    });
});

function sortTable(table, columnIndex, dataType, ascending) {
    let tbody = table.querySelector("tbody");
    let tfoot = table.querySelector("tfoot");
    let rows = Array.from(tbody.getElementsByTagName("tr"));
    rows.push(...Array.from(tfoot.getElementsByTagName("tr"))); // Include footer rows

    let isNumber = dataType === "number";
    rows.shift(); // Remove the header row

    rows.sort(function (rowA, rowB) {
        let cellA = rowA.getElementsByTagName("td")[columnIndex].innerText;
        let cellB = rowB.getElementsByTagName("td")[columnIndex].innerText;
        if (isNumber) {
            return (parseInt(cellA) - parseInt(cellB)) * (ascending ? 1 : -1);
        } else {
            return cellA.localeCompare(cellB) * (ascending ? 1 : -1);
        }
    });

    // Remove all existing rows from tbody and tfoot
    tbody.innerHTML = "";
    tfoot.innerHTML = "";

    rows.forEach(function (row) {
        if (row.parentNode === tbody) {
            tbody.appendChild(row);
        } else if (row.parentNode === tfoot) {
            tfoot.appendChild(row);
        }
    });
}

function updateSortIndicator(th) {
    let indicator = th.querySelector(".sort-indicator");
    if (!indicator) {
        indicator = document.createElement("span");
        indicator.classList.add("sort-indicator");
        th.appendChild(indicator);
    }
    indicator.innerHTML = sortAscending ? "&#9660;" : "&#9650;";
}
