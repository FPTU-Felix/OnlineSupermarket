/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

fetch(`orders/api/v1`)
        .then(response => {
            if (!response.ok)
                throw new Error("Network response was not ok");
            return response.json();
        })
        .then(data => {
            // Fill modal content
            let tbody = document.getElementById('order-body');
            tbody.innerHTML = "";
            data.forEach(({ orderID, customerName, receiverName, statusName, paymentMethod, createdOrder }) => {
                let row = document.createElement('tr');
                row.innerHTML = `
                                            <td>${orderID}</td>
                                            <td>${customerName}</td>
                                            <td>
                                                <button type="button" class="view-receiver btn btn-link fw-bold text-decoration-underline text-black" data-bs-toggle="modal" data-bs-target="#view-order-receiver" data-id="${orderID}">
                                                    ${receiverName}
                                                </button>
                                            </td>
                                            <td>
                                                <button type="button" class="view-details btn btn-link fw-bold text-decoration-underline text-black" data-bs-toggle="modal" data-bs-target="#view-order-details" data-id="${orderID}">
                                                    View
                                                </button>
                                            </td>
                                            <td>${statusName}</td>
                                            <td>${paymentMethod}</td>
                                            <td>${createdOrder}</td>
                                        `;
                tbody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching orders:', error);
        });
