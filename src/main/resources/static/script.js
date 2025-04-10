function addProduct() {
    const product = {
        name: document.getElementById("productName").value,
        quantity: parseInt(document.getElementById("productQuantity").value),
        volume: parseFloat(document.getElementById("productVolume").value),
        warehouse: { id: parseInt(document.getElementById("productWarehouseId").value) }
    };
    fetch('/api/products', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(product)
    }).then(response => response.json())
        .then(data => alert("Товар добавлен: " + data.name))
        .then(loadProducts);
}

function addWarehouse() {
    const warehouse = {
        name: document.getElementById("warehouseName").value,
        location: document.getElementById("warehouseLocation").value,
        capacity: parseFloat(document.getElementById("warehouseCapacity").value)
    };
    fetch('/api/warehouses', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(warehouse)
    }).then(response => response.json())
        .then(data => alert("Склад добавлен: " + data.name))
        .then(loadWarehouses);
}

function loadProducts() {
    fetch('/api/products')
        .then(response => response.json())
        .then(data => {
            const productsDiv = document.getElementById("products");
            productsDiv.innerHTML = data.map(p => `<p>${p.name} (кол-во: ${p.quantity}, объем: ${p.volume}, склад: ${p.warehouse.name})</p>`).join("");
        });
}

function loadWarehouses() {
    fetch('/api/warehouses')
        .then(response => response.json())
        .then(data => {
            const warehousesDiv = document.getElementById("warehouses");
            warehousesDiv.innerHTML = data.map(w => `<p>${w.name} (${w.location}, объем: ${w.capacity})</p>`).join("");
        });
}

function searchWarehouses() {
    const productName = document.getElementById("searchProductName").value;
    fetch(`/api/warehouses/by-product/${productName}`)
        .then(response => response.json())
        .then(data => {
            const results = document.getElementById("searchResults");
            results.innerHTML = data.map(w => `<p>${w.name} (${w.location})</p>`).join("");
        });
}

document.addEventListener("DOMContentLoaded", () => {
    loadProducts();
    loadWarehouses();
});