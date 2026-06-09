<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<h3 class="page-title mb-5">Customer Service</h3>
				<div>
					<div class="list-group list-group-transparent mb-0">
						<a href="clientDetailsForm"
							class="list-group-item list-group-item-action d-flex align-items-center">
							<span class="icon mr-3"><i class="fe fe-user"></i></span>Customer
							Details
						</a> <a href="cardDetailsForm"
							class="list-group-item list-group-item-action d-flex align-items-center">
							<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Card
							Details
						</a> <a href="accountDetailsForm"
							class="list-group-item list-group-item-action d-flex align-items-center">
							<span class="icon mr-3"><i class="fa fa-newspaper-o"></i></span>Account
							Details
						</a> <a href="addressDetailsForm"
							class="list-group-item list-group-item-action d-flex align-items-center">
							<span class="icon mr-3"><i class="fa fa-address-book"></i></span>Address
							Details
						</a> <a href="contactDetailsForm"
							class="list-group-item list-group-item-action d-flex align-items-center">
							<span class="icon mr-3"><i class="fe fe-phone"></i></span>Contact
							Details
						</a> <a href="cardManagementForm"
							class="list-group-item list-group-item-action d-flex align-items-center active">
							<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Card
							Management
						</a>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="card">
					<form:form action="emailDetailsForm" method="post"
						commandName="emailBean" name="emailDetails" id="emailDetailsForm">
						<div class="card-header">
							<h3 class="card-title">Card Renew</h3>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<label class="form-label">Type</label> <select
											id="addressType" class="form-control custom-select">
											<option value="1">Card Replace with Old Card</option>
											<option value="2">Card Replace with New Card</option>
										</select>
									</div>
								</div>

								<div class="col-md-6 col-lg-6">
									<div class="form-group">
										<label class="form-label">Comment</label> <input type="text"
											class="form-control">
									</div>
								</div>
							</div>
						</div>
						<div class="card-footer text-right">
							<div class=".d-flex1">
								<button type="submit" id="submitBtn" name="strRequestBtn"
									value="1" class="btn btn-primary ml-auto">Submit</button>
								<button type="submit" class="btn btn-primary ml-auto"
									name="strRequestBtn" value="2" id="resetBtn">Clear</button>
							</div>
						</div>
					</form:form>
				</div>

				<div class="card">

					<div class="card-header">
						<h3 class="card-title">Recard</h3>
					</div>
					<div class="card-body"></div>
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn" name="strRequestBtn"
								value="1" class="btn btn-primary ml-auto">Submit</button>
							<button type="submit" class="btn btn-primary ml-auto"
								name="strRequestBtn" value="2" id="resetBtn">Clear</button>
						</div>
					</div>
				</div>
				
				<div class="card">

					<div class="card-header">
						<h3 class="card-title">New Card</h3>
					</div>
					<div class="card-body">
						<button type="submit" id="submitBtn" name="strRequestBtn"
								value="1" class="btn btn-primary ml-auto">New Card</button>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
