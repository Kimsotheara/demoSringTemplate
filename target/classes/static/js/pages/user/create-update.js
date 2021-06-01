//$(document).ready(function () {
//
//    const userApiUrl = "/api/v1/users";
//    let data = {};
//    let username;
//
//    const $username = $("input[name='username']");
//    const $pwd = $("input[name='pwd']");
//    const $confPwd = $("input[name='confPwd']");
//    const $seller = $("select.select-seller").children("option:selected");
//
//    $("select.select-seller").change(function () {
//        const selectedSellerId = $(this).children("option:selected").data('id');
//        const selectedSellerName = $(this).children("option:selected").data('name');
//        username = getAvailableUsername(selectedSellerName);
//        $seller.val(selectedSellerId)
//    });
//
//    $('[data-btn="save"]').on("click", function () {
//        if (!validateAndAssignData()) return false;
//        addUser();
//    });
//
//    $('[data-btn="update"]').on("click", function () {
//        if (!validateAndAssignData()) return false;
//        data.id = userId;
//        updateUser();
//    });
//
//    function getAvailableUsername(sellerName) {
//        sellerName = sellerName.toLowerCase().split(' ').join('');
//        getRequest(userApiUrl + '/username/' + sellerName, response => {
//            const dataJson = response.responseJSON;
//            if (dataJson.statusCode !== 200) {
//                toastAlertError(dataJson.message)
//            }
//            $username.val(dataJson.data)
//        })
//    }
//
//    function validateAndAssignData() {
//        /* Get input element */
//        /* Get input value */
//        const username = $username.val();
//        const pwd = $pwd.val();
//        const confPwd = $confPwd.val();
//        const seller = $seller.val();
//        const role = $('#role').select2('val');
//
//
//        if (!seller || seller === '0') {
//            toastAlertError("Please select a seller");
//            $seller.focus();
//            return false;
//        }
//
//        let isValidate = validateInputField(
//            [
//                $username,
//                $pwd,
//                $confPwd
//            ]
//        );
//
//        if (!isValidate) {
//            return false;
//        }
//
//        if (pwd !== confPwd) {
//            toastAlertError("Password not match");
//            $confPwd.focus();
//            return false;
//        }
//
//        data = {
//            "username": username,
//            "password": pwd,
//            "role": role,
//            "seller": {
//                "id": +seller
//            },
//            "status": true
//        };
//        return data;
//    }
//
//    function addUser() {
//        // console.log(data);
//        postRequest(userApiUrl, data, function (dataRes) {
//            if (dataRes.status === 200 && dataRes.responseJSON.statusCode === 200) {
//                Swal.fire({
//                    icon: 'success',
//                    title: 'User created.',
//                    showConfirmButton: false,
//                    timer: 1500
//                }).then(() => $(location).attr('href', "/users"))
//            } else {
//                let errorAlert = dataRes.responseJSON.message;
//                toastAlertError(errorAlert);
//            }
//        })
//    }
//
//    function updateUser() {
//        putRequest(userApiUrl, data, function (dataRes) {
//            if (dataRes.status === 200 && dataRes.responseJSON.statusCode === 200) {
//                Swal.fire({
//                    icon: 'success',
//                    title: 'User updated.',
//                    showConfirmButton: false,
//                    timer: 1500
//                }).then(() => $(location).attr('href', "/users"))
//            } else {
//                let errorAlert = dataRes.responseJSON.message;
//                toastAlertError(errorAlert);
//            }
//        })
//    }
//
//});