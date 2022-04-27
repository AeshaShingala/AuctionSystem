var pushedBid;
setInterval(async function () {
    //await delay();
    var response = await fetch('/data');
    var myJson = await response.json();
    // console.log(myJson)
    // console.log(myJson.userName)
    $("#currentBid")[0].innerHTML = myJson.bid;
    $("#newBid")[0].value = myJson.increment;
    $("#userName")[0].innerHTML = myJson.userName;
    if (pushedBid != currentBid.innerHTML){
        $("#highBidAlert").hide()
        $("#outBidAlert").show()
        $("#submitBid").prop("disabled", false)
    }
}, 2000);

function sendBid() {

    console.log("called sendBid.");
    let bidForm = document.getElementById("team");
    let formData = new FormData(bidForm);
    pushedBid = formData.get("bid");
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/bids",
        data: formData,
        processData: false, contentType: false,
        success: function (result) {
            alert("sending data.");
            if (result == "success") {
                console.log("bid send.");
            }
        }

    });
    $(function () {
        $(document).keydown(function (e) {
            return (e.which || e.keyCode) != 116;
        });
    });
    $("#highBidAlert").show()
    $("#outBidAlert").hide()
    console.log("disabled")
    $("#submitBid").prop("disabled", true)
}