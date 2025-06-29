/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function () {
    const tbody = document.getElementById('order-body');
    const orderDetailsModal = document.getElementById('view-order-receiver');
    tbody.addEventListener('click', function (event) {
        const button = event.target.closest('.view-receiver');
        if (!button)
            return;
        let orderId = button.getAttribute('data-id');
        console.log(orderId);
        fetch(`orders/api/v1?orderID=${orderId}`)
                .then(response => {
                    if (!response.ok)
                        throw new Error("Network response was not ok");
                    return response.json();
                })
                .then(([{ receiverName, receiverGender, receiverEmail, receiverMobile, receiverAddress }]) => {
                    let tbody = document.getElementById('order-receiver-body');
                    tbody.innerHTML = "";

                    const fields = [
                        ["Name", receiverName],
                        ["Gender", receiverGender],
                        ["Email", receiverEmail],
                        ["Phone Number", receiverMobile],
                        ["Address", receiverAddress]
                    ];

                    fields.forEach(([label, value]) => {
                        const row = document.createElement('tr');
                        row.innerHTML = `<td><strong>${label}:</strong></td><td>${value}</td>`;
                        tbody.appendChild(row);
                    });
                }).catch(error => {
            console.error('Error fetching order receiver details:', error);
        });
    });
});
