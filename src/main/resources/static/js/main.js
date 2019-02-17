$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

});

function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];
    var data =  { "fileName": $('input[name=fileName]:checked').val()};
    console.log(data);
    $("#btnSubmit").prop("disabled", true);
    $('#myTable').empty();
    $( '#result' ).empty();
    $.ajax({
        type: "POST",
        url: "/api/v1/stament/processor",
        data: JSON.stringify(data),
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        dataType: "json",
        contentType: 'application/json',
        success: function (response) {
        	$('table').append('<tr><td> Reference </td><td> Description </td></tr>');
            $.each(response, function(idx, obj) {
            	$('table tr:last')
                .after('<tr><td>' + obj.reference+ '</td>' +
                    '<td>' + obj.description+ '</td>' +
                    '</tr>');
            });
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}
