//$(document).ready(function () {
//    (function () {
//        const baseUrl = "/api/v1/users";
//
//        const userDatatable = $('#data-table').DataTable({
//            responsive: true,
//            "ajax": {
//                "url": baseUrl,
//                "type": "GET",
//                "dataSrc": function (d) {
//                    return d.data
//                }
//            },
//            "columnDefs": [
//                {"className": "dt-center", "targets": "_all"}
//            ],
//            "columns": [
//                {"data": null},
//                {"data": "username"},
//                {"data": "role"},
//                {"data": null, "width": "5%"}
//            ],
//            "createdRow": function (row, data) {
//                const $cell = $(row).find("td");
//                const id = data.id;
//                const linkUserUpdate = "<div class='ellipsis'><a class='link' href='/users/update/" + id + "' data-btn='linkUserUpdate' title='" + id + "'>" + id + "</a></div>";
//                const btnDelete = "<button type='button' class='btn btn-sm btn-danger' data-btn='delete' data-seq='" + id + "' title='Delete'>Delete</button>";
//
//                $cell.eq(0).html(linkUserUpdate);
//                $cell.eq(3).html(btnDelete);
//            }
//        });
//
//        // reload datatable
//        function reLoadTable() {
//            userDatatable.ajax.reload();
//        }
//
//        // open delete message popup
//        let deleteUserId = "";
//        $(document).on("click", "[data-btn='delete']", function () {
//            deleteUserId = $(this).attr("data-seq");
//            toastAlertConfirm(
//                'Delete',
//                'Do you want to delete this user?',
//                'warning',
//                true,
//                "Delete",
//                "#dc3545")
//                .then(result => {
//                    if (result.value) {
//                        deleteRequest("api/v1/users/" + deleteUserId, response => {
//                            if (response.status === 200) {
//                                toastAlertSuccess("User deleted!");
//                                reLoadTable();
//                            } else {
//                                toastAlertError(response.responseJSON.message)
//                            }
//                            $(this).prop("disabled", false);
//                        });
//                    }
//                })
//        });
//    }());
//});