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
    let rows = Array.from(table.getElementsByTagName("tr"));
    let isNumber = dataType === "number";
    rows.shift(); // Loại bỏ hàng tiêu đề

    rows.sort(function (rowA, rowB) {
        let cellA = rowA.getElementsByTagName("td")[columnIndex].innerText;
        let cellB = rowB.getElementsByTagName("td")[columnIndex].innerText;
        if (isNumber) {
            return (parseInt(cellA) - parseInt(cellB)) * (ascending ? 1 : -1);
        } else {
            return cellA.localeCompare(cellB) * (ascending ? 1 : -1);
        }
    });
    let tbody = table.querySelector("tbody");
    rows.forEach(function (row) {
        tbody.appendChild(row);
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
