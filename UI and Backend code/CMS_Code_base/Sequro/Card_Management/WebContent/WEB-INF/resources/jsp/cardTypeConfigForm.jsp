<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
var deleteBinID;
var cardData;

$(document)
    .ready(
        function() {

            $('#strSelectID').val("N");

            $('#strBinSuffixFlag').hide();

            $('#delBinMsg').text("");

            $('#strCardType').keyup(
                function() {
                    var txt = $('#strCardType').val().replace(
                        /(?:^|)\w/g,
                        function(match) {
                            return match.toUpperCase();
                        });
                    $('#strCardType').val(txt);
                });

            networkScheme($('#strBin').val());

            $('#strBin').change(function() {
                networkScheme($(this).val());
            });

            $('#cardTypeTable').DataTable({
            	"scrollX" : "50px",
				"scrollCollapse" : true,
				responsive : true
            });

            /* $('#mccListTable').DataTable({
            	responsive : true
            }); */

            $('#deleteBtn')
                .click(function() {
                    $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "${pageContext.request.contextPath}/deleteCardType",
                        data: "id=" +
                            deleteBinID,
                        dataType: 'json',
                        contentType: "application/x-www-form-urlencoded",
                        timeout: 100000,
                        success: function(data) {
                            console.log("SUCCESS: ", data);
                            if (data.outResponseCode == "00") {
                                $('#delBinMsg').addClass("tag tag-green");
                                $('#delBinMsg').text(data.message);
                                setTimeout(
                                    function() { // wait for 5 secs(2)
                                        var obj = {
                                            Title: '',
                                            Url: 'cardTypeConfigForm'
                                        };
                                        history.pushState(obj, obj.Title, obj.Url);
                                        location.reload(); // then reload the page.(3)
                                    }, 2000);
                            } else {
                                $('#delBinMsg').addClass("tag tag-red");
                                $('#delBinMsg').text(data.message);
                            }
                        },
                        error: function(e) {
                            console.log("ERROR: ", e);
                            $('#delBinMsg').addClass("tag tag-red");
                            $('#delBinMsg').text("Error");
                        },
                        done: function(e) {
                            console.log("DONE");
                        }
                    });

                    return false;
                })

            $.validator.addMethod("regex",
                function(value, element, regexp) {
                    var check = false;
                    return this.optional(element) ||
                        regexp.test(value);
                }, "ss");

            var value = $("#cardTypeConfigForm")
                .validate({
                    rules: {
                        strCardType: {
                            required: true,
                            regex: /^[a-zA-Z]+$/,
                            minlength: 3,
                            maxlength: 5
                        },
                        strCardDesc: {
                            required: true,
                            regex: /^[a-zA-Z0-9\s.\-]+$/,
                            minlength: 1,
                            maxlength: 30
                        },
                        strBin: {
                            required: true
                        },
                        strBinSuffix: {
                            required: true,
                            regex: /^[0-9]+$/,
                            minlength: 1,
                            maxlength: 2
                        },
                        strCardGenerationType: {
                            required: false
                        }
                    },
                    messages: {
                        strCardType: {
                            required: "Please Enter Card Type!",
                            minlength: "Minmum {0} characters required!",
                            maxlength: "Maximum {0} characters allowed!",
                            regex: "Only Char allow !"
                        },
                        strCardDesc: {
                            required: "Please Enter Card Desc!",
                            minlength: "Minmum {0} characters required!",
                            maxlength: "Maximum {0} characters allowed!",
                            regex: "Only Char allow !"
                        },
                        strBin: {
                            required: "Required !",
                        },
                        strBinSuffix: {
                            required: "Please Enter Bin Suffix!",
                            minlength: "Minmum {0} characters required!",
                            maxlength: "Maximum {0} characters allowed!",
                            regex: "Only Numeric allow !"
                        },
                        strCardGenerationType: {
                            required: "Required !",
                        },
                    },
                    submitHandler: function(form) {

                        if ($('#strSelectID').val() == "N") {

                            if (form.strCardGenerationType.value == "") {
                                $(
                                        '#strCardGenerationTypeError')
                                    .text(
                                        "Required !");
                                return false;
                            } else {
                                $(
                                        '#strCardGenerationTypeError')
                                    .text("");
                            }

                            var data = eval('${cardType}');
                            var flag = false;
                            var binVal = form.strCardType.value;
                            var binVls = form.strBin.value;
                            var binSuffix = form.strBinSuffix.value;
                            $.each(data, function(index, value) {
                                if (binVal == JSON.stringify(value.strCardType).replace("\"", "").replace("\"", "")) {
                                    flag = true;
                                    return false;
                                }
                            });
                            if (flag) {
                                $('#strError').text("Duplicate Card Type");
                                $('#errorMsg').hide();
                            } else {
                                $.each(data, function(index, value) {
                                    if (binVls == JSON.stringify(value.strBin).replace("\"", "").replace("\"", "") && binSuffix == JSON.stringify(value.strBinSuffix)
                                        .replace("\"", "").replace("\"", "")) {
                                        flag = true;
                                        return false;
                                    }
                                });
                                if (flag) {
                                    $('#strError')
                                        .text(
                                            "Duplicate Bin and Suffix !");
                                    $('#errorMsg')
                                        .hide();
                                } else {
                                    $('#strError')
                                        .text("");
                                    $('#errorMsg')
                                        .hide();
                                    form.submit();
                                }
                            }
                        } else {
                            cardData.strCardDesc = form.strCardDesc.value;
                            cardData.strBin = form.strBin.value;
                            cardData.strBinSuffix = form.strBinSuffix.value;
                            cardData.strFlag = form.strFlag.value;
                            cardData.strEndpoint = form.strEndpoint.value;
                            cardData.strMcc = "";
                            $.each($("input[name='strMcc']:checked"), function() {
                                cardData.strMcc += $(this).val() + ",";
                            });
                            //cardData.strMcc = form.strMcc.value;
                            //cardData.strCardGenerationType = form.strCardGenerationType.value;
                            $
                                .ajax({
                                    type: "POST",
                                    contentType: "application/json",
                                    url: "${pageContext.request.contextPath}/updateCardType",
                                    data: JSON
                                        .stringify(cardData),
                                    dataType: 'json',
                                    contentType: "application/json",
                                    timeout: 100000,
                                    success: function(
                                        data) {
                                        console.log("SUCCESS: ", data);
                                        if (data.outResponseCode == "00") {
                                            $('#strError').addClass("tag tag-green");
                                            $('#strError').text(data.message);
                                            setTimeout(
                                                function() { // wait for 5 secs(2)
                                                    var obj = {
                                                        Title: '',
                                                        Url: 'cardTypeConfigForm'
                                                    };
                                                    history
                                                        .pushState(
                                                            obj,
                                                            obj.Title,
                                                            obj.Url);
                                                    location
                                                        .reload(); // then reload the page.(3)
                                                },
                                                2000);
                                        } else {
                                            $(
                                                    '#strError')
                                                .addClass(
                                                    "tag tag-red");
                                            $(
                                                    '#strError')
                                                .text(
                                                    data.message);
                                        }
                                    },
                                    error: function(
                                        e) {
                                        console
                                            .log(
                                                "ERROR: ",
                                                e);
                                        $(
                                                '#strError')
                                            .addClass(
                                                "tag tag-red");
                                        $(
                                                '#strError')
                                            .text(
                                                "Error");
                                    },
                                    done: function(
                                        e) {
                                        console
                                            .log("DONE");
                                    }
                                });
                        }
                    }
                });

        });

function editFunction(bin) {

    $("#strEndpoint").val("");

    $('#iCardTokenFlagDiv').hide();
    
    var data = eval('${cardType}');
    $.each(data, function(index, value) {
        if (bin == JSON.stringify(value.strCardType).replace("\"", "")
            .replace("\"", "")) {
            cardData = value;
            return false;
        }
    });


    $('input:checkbox').removeAttr('checked');

    //$('#crdGen').hide();
    $("#strEndpoint > option").each(function() {
        if (cardData.strEndpoint == this.text) {
            $("#strEndpoint").val(this.value);
            return false;
        }
    });

    networkScheme(cardData.strBin);
    $('#strCardType').val(cardData.strCardType);
    $('#strCardType').attr('readonly', true);
    $('#strCardDesc').val(cardData.strCardDesc);
    $('#strBin').val(cardData.strBin);
    $('#strBinSuffix').val(cardData.strBinSuffix);
    $('#strFlag').val(cardData.strFlag);
    $('#strSelectID').val("Y");
    $('#strCardGenerationType').val(cardData.strCardGenerationType);

}

function deleteFunction(id) {
    $('#delBinMsg').text("");
    $('#strCardType').val("");
    $('#strCardDesc').val("");
    $('#strBin').val("");
    deleteBinID = id;
    $('#myModal').modal('show');
}

function networkScheme(bin) {

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "${pageContext.request.contextPath}/getNetworkScheme",
        data: "id=" + bin,
        dataType: 'json',
        contentType: "application/x-www-form-urlencoded",
        timeout: 100000,
        success: function(data) {
            console.log("SUCCESS: ", data);
            if (data.outResponseCode == "00") {
                $('#networkScheme').val(data.message);
            }
            if (data.flag == "Y") {
                $('#strBinSuffixFlag').show();
            } else {
                $('#strBinSuffixFlag').hide();
            }
        },
        error: function(e) {
            console.log("ERROR: ", e);
        },
        done: function(e) {
            console.log("DONE");
        }
    });

    return false;

}
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
		<div class="col-md-3">
				<jsp:include page="binCardConfigLeftMenu.jsp"></jsp:include>
			</div>
			<div class="col-md-9">
				<form:form action="cardTypeConfig" method="post"
					commandName="cardTypeModel" name="cardTypeConfigForm"
					id="cardTypeConfigForm" cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Card Type
							Configuration <span class="text-right badge badge-danger"
								id="strError"></span>
						</h3>
						<div class="text-right" style="display: ${display};" id="errorMsg">
							<c:if test="${not empty exception}">
								<div class="text-right badge badge-danger"
									style="display: ${display};">
									<c:out value="${exception}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class="text-right badge badge-success"
									style="display: ${display};">
									<c:out value="${success}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="text-right badge badge-danger"
									style="display: ${display};">
									<c:out value="${error}"></c:out>
								</div>
							</c:if>
						</div>
					</div>
					<div class="card-body">
						<div class="row">

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Card Type <b>*</b></label>
									<form:input path="strCardType" id="strCardType"
										cssClass="form-control" />
									<span class="error" id="strCardTypeError"><form:errors
											path="strCardType"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Description <b>*</b></label>
									<form:input path="strCardDesc" id="strCardDesc"
										cssClass="form-control" />
									<span class="error" id="strCardDescError"><form:errors
											path="strCardDesc"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Bin <b>*</b></label>
									<form:select path="strBin" id="strBin"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${binList}" var="itr">
											<form:option value="${itr.strBin}">${itr.strBin}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strBinError"><form:errors
											path="strBin"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6" id="strBinSuffixFlag">
								<div class="form-group">
									<label class="form-label">Bin Suffix <b>*</b></label>
									<form:input path="strBinSuffix" id="strBinSuffix"
										cssClass="form-control" />
									<span class="error" id="strBinSuffixError"><form:errors
											path="strBinSuffix"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Flag <b>*</b></label>
									<form:select path="strFlag" id="strFlag"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<form:option value="1">Magstripe</form:option>
										<form:option value="2">Magncmc</form:option>
										<form:option value="3">EMV</form:option>
									</form:select>
									<span class="error" id="strFlagError"><form:errors
											path="strFlag"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Network Scheme</label> <input
										type="text" name="networkScheme" class="form-control"
										id="networkScheme" readonly="readonly"> <span
										class="error" id=""networkScheme"Error"></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Endpoint <b></b></label>
									<form:select path="strEndpoint" id="strEndpoint"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${endpoint}" var="itr">
											<form:option value="${itr.lkpkey}">${itr.lkpvalue}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strEndpointError"><form:errors
											path="strEndpoint"></form:errors></span>
								</div>
							</div>

							<div class="col-md-6 col-lg-6" id="crdGen">
								<div class="form-group">
									<label class="form-label">Card Generation Type <b>*</b></label>
									<form:select path="strCardGenerationType"
										id="strCardGenerationType"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<form:option value="R">Random</form:option>
										<form:option value="S">Sequential</form:option>
									</form:select>
									<span class="error" id="strCardGenerationTypeError"><form:errors
											path="strCardGenerationType"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6" id="iCardTokenFlagDiv">
								<div class="form-group">
									<label class="form-label">Generate Token <b>*</b></label>
									<form:select path="iCardTokenFlag"
										id="iCardTokenFlag"
										cssClass="form-control selectpicker">
										<form:option value="1">Yes</form:option>
										<form:option value="0">No</form:option>
									</form:select>
									<span class="error" id="iCardTokenFlagError"><form:errors
											path="iCardTokenFlag"></form:errors></span>
								</div>
							</div>
							
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Account Type <b></b></label>
									<form:select path="strAccountType" id="strAccountType"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${accountTypeList}" var="itr">
											<form:option value="${itr.strAccountType}">${itr.strAccountType}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strAccountTypeError"><form:errors
											path="strAccountType"></form:errors></span>
								</div>
							</div>
							
							<%-- <div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">CVV Method <b>*</b></label>
									<form:input path="strCVN" id="strCVN"
										cssClass="form-control" />
									<span class="error" id="strCVNError"><form:errors
											path="strCVN"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Pin Mailer Format <b>*</b></label>
									<form:input path="strPinFormat" id="strPinFormat"
										cssClass="form-control" />
									<span class="error" id="strPinFormatError"><form:errors
											path="strPinFormat"></form:errors></span>
								</div>
							</div>
							
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Decimalization Table <b>*</b></label>
									<form:input path="strDecTable" id="strDecTable"
										cssClass="form-control" />
									<span class="error" id="strDecTableError"><form:errors
											path="strDecTable"></form:errors></span>
								</div>
							</div> --%>


							<%-- <div class="card-body">
								<label class="form-label">MCC Code </label>
								<table id="mccListTable"
									class="table table-striped table-bordered nowrap"
									style="width: 100%">
									<thead>

									</thead>
									<tbody>
										<c:forEach var="itr" items="${mccList}">
											<tr>
												<td> 
												<label class="custom-control custom-checkbox custom-control-inline">
													<form:checkbox  path="strMcc" id="${itr.lkpkey}" class="custom-control-input" value="${itr.lkpkey}"  />
		                            				<span class="custom-control-label"><c:out value="${itr.lkpvalue}" /></span>
		                          				</label>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div> --%>
						</div>
					</div>

					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
							<button type="reset" class="btn btn-primary ml-auto"
								id="resetBtn">Clear</button>
						</div>
					</div>

					<div class="card-body">
						<table id="cardTypeTable"
							class="table table-striped table-bordered nowrap"
							style="width: 100%">
							<thead>
								<tr>
									<th>Card Type</th>
									<th>Description</th>
									<th>Bin</th>
									<th>Bin Suffix</th>
									<th>Bin Suffix Flag</th>
									<th>Endpoint</th>
									<th>Card Generation Type</th>
									<!-- <th>Pin Mailer Format</th>
							<th>Decimalization Table</th> -->
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="itr" items="${cardTypeList}">
									<tr>
										<td>${itr.strCardType}</td>
										<td>${itr.strCardDesc}</td>
										<td>${itr.strBin}</td>
										<td>${itr.strBinSuffix}</td>
										<td><c:choose>
												<c:when test="${itr.strFlag eq 1}">
										Magstripe
									</c:when>
												<c:when test="${itr.strFlag eq 2}">
										Magncmc
									</c:when>
												<c:when test="${itr.strFlag eq 3}">
										EMV
									</c:when>
											</c:choose></td>
										<td>${itr.strEndpoint == '' ? 'NA' : itr.strEndpoint}</td>
										<td>${itr.strCardGenerationType == 'R' ? "Random" : "Sequential"}</td>
										<%-- <td>${itr.strPinFormat}</td>
								<td>${itr.strDecTable}</td> --%>
										<td><a class="icon"
											href="javascript:editFunction('${itr.strCardType}')"> <i
												class="fe fe-edit"></i>
										</a> &nbsp;&nbsp;&nbsp; <a class="icon"
											href="javascript:deleteFunction(${itr.strID})"> <i
												class="fe fe-trash"></i>
										</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="hidden" value="${leftbinCardMenuID}"
								id="leftbinCardMenuID">
								
					</div>

					<form:hidden path="strSelectID" id="strSelectID" />
					<input type="hidden" name="strCardIssue" value="0">
					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<p class="text-capitalize">
										Delete Card Type <label id="delBinMsg"></label>
									</p>
								</div>
								<div class="modal-body">

									<form>
										<p class="leading-loose">Are you sure you want to delete
											this Card Type ?</p>
									</form>
								</div>


								<div class="modal-footer">
									<button type="button" class="btn btn btn-red" id="deleteBtn">Delete</button>
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
					<!-- Modal -->

				</form:form>
			</div>
		</div>
	</div>
</div>
