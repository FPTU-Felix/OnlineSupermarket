/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', function () {
    const tbody = document.getElementById('order-body');
    const orderDetailsModal = document.getElementById('view-order-details');

    tbody.addEventListener('click', function (event) {
        const button = event.target.closest('.view-details');
        if (!button)
            return;

        let orderId = button.getAttribute('data-id');
        fetch(`order-detail/api/v1`)
                .then(response => {
                    if (!response.ok)
                        throw new Error("Network response was not ok");
                    return response.json();
                })
                .then(data => {
                    data2 = data?.filter(item => item.orderID == orderId);
                    let tbody = document.getElementById('order-details-body');
                    tbody.innerHTML = "";

                    data2.forEach(item => {
                        let row = document.createElement('tr');
                        row.innerHTML = `
                        <td>${item.title}</td>
                        <td>${item.quantity}</td>
                        <td><img src="/OnlineSupermarket/images/${item.thumbnail}" alt="Thumb" style="width:50px;"></td>
                        <td>${item.price}</td>
                    `;
                        tbody.appendChild(row);
                    });
                }).catch(error => {
            console.error('Error fetching order details:', error);
        });
    });
});
