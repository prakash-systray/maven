               
               
  <footer class="footer" style="background-color: #343a40;">
            <div class="container">
                WAYUPARTY © 2019. Copyrights all rights reserved. Developed by Acculytixs Pvt Ltd.
            </div>
 </footer>
  
  
   <script src="/resources/js/jquery-3.3.1.min.js"></script>
    <!-- Plugins js -->
    <script src="/resources/js/plugins.js"></script>
    <!-- Popper js -->
    <script src="/resources/js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="/resources/js/bootstrap.min.js"></script>
    
     <!-- Select 2 Js -->
    <script src="/resources/js/select2.min.js"></script>
    <!-- Date Picker Js -->
    <script src="/resources/js/datepicker.min.js"></script>
    <!-- Custom Js -->
    
    <!-- Counterup Js -->
    <script src="/resources/js/jquery.counterup.min.js"></script>
    <!-- Moment Js -->
    <script src="/resources/js/moment.min.js"></script>
    <!-- Waypoints Js -->
    <script src="/resources/js/jquery.waypoints.min.js"></script>
    <!-- Scroll Up Js -->
    <script src="/resources/js/jquery.scrollUp.min.js"></script>
    <!-- Full Calender Js -->
    <script src="/resources/js/fullcalendar.min.js"></script>
    <!-- Chart Js -->
    <script src="/resources/js/Chart.min.js"></script>
    <!-- Data Table Js -->
    <script src="/resources/js/jquery.dataTables.min.js"></script>
    <!-- Custom Js -->
    <script src="/resources/js/main.js"></script>
    
       
     <script src="/resources/js/common.js" type="text/javascript"></script>
     <script src="/resources/js/formvalidator.js" type="text/javascript"></script>
    
		<script type="text/javascript">
		var tokenParamName = "${_csrf.parameterName}";
		var tokenValue = "${_csrf.token}";
		var appUrl = "${Wayuparty_appUrl}";
		
		
		  $(document).bind("contextmenu",function(e){
		      return false;
		   }); 
		 
		 document.onkeydown = function(e) {
			  if(event.keyCode == 123) {
			     return false;
			  }
			  if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)) {
			     return false;
			  }
			  if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)) {
			     return false;
			  }
			  if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)) {
			     return false;
			  }
			  if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)) {
			     return false;
			  }
			}
		</script>
