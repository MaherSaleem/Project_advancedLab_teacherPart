<head>
    <link rel="stylesheet" type="text/css" href="css_for_main_page.css">

</head>


<ul class="nav nav-pills">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="Add_student.php">new Stdent</a></li>
    <li ><a href="Quiz.php">New Quiz</a></li>
    <li ><a href="Grades.php">Grades</a></li>

    </li>
</ul>

<?php
/**
 * Created by PhpStorm.
 * User: Maher
 * Date: 12/27/2016
 * Time: 2:33 PM
 */


echo "<br><br><button  onclick=\"deactivate()\" class='btn btn-warning' style='margin-left: 20px'>Deactivate the Quiz</button>";
echo "<h1>Add New Qustion For Quiz#" .$_GET["qid"]."</h1>";


#SELECT max(questions.qNum) from questions WHERE questions.quiz_id=1



?>
<!-- Special version of Bootstrap that only affects content wrapped in .bootstrap-iso -->
<link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />

<!-- Inline CSS based on choices in "Settings" tab -->
<style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form{font-family: Arial, Helvetica, sans-serif; color: black}.bootstrap-iso form button, .bootstrap-iso form button:hover{color: white !important;} .asteriskField{color: red;}.bootstrap-iso form .input-group-addon {color:#555555; background-color: #f7dddd; border-radius: 4px; padding-left:12px}</style>

<!-- HTML Form (wrapped in a .bootstrap-iso div) -->
<div class="bootstrap-iso">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-12">
                <form method="post" action="http://localhost:8080/Project/quizes/qustion">
                    <div class="form-group ">
                        <label class="control-label requiredField" for="name">
                            Question Id
       <span class="asteriskField">
        *
       </span>
                        </label>
                        <input class="form-control" id="qNum" name="qNum" type="text"/>
                    </div>
                    <div class="form-group ">
                        <label class="control-label requiredField" for="question">
                            Question
       <span class="asteriskField">
        *
       </span>
                        </label>
                        <input class="form-control" id="question" name="question" type="text"/>
                    </div>
                    <div class="form-group ">
                        <label class="control-label requiredField" for="ans1">
                            choice 1
       <span class="asteriskField">
        *
       </span>
                        </label>
                        <input class="form-control" id="ans1" name="ans1" type="text"/>
                    </div>
                    <div class="form-group ">
                        <label class="control-label requiredField" for="name3">
                            choice 2
       <span class="asteriskField">
        *
       </span>
                        </label>
                        <input class="form-control" id="ans2" name="ans2" type="text"/>
                    </div>
                    <div class="form-group ">
                        <label class="control-label requiredField" for="name4">
                            choice 3
       <span class="asteriskField">
        *
       </span>
                        </label>
                        <input class="form-control" id="ans3" name="ans3" type="text"/>
                    </div>
                    <div class="form-group ">
                        <label class="control-label requiredField" for="name5">
                            Correct Answer
       <span class="asteriskField">
        *
       </span>
                        </label>
                        <input class="form-control" id="correct_ans" name="correct_ans" type="text"/>
                    </div>
                    <input type="hidden" name="quiz_id" value="<?php echo $_GET["qid"] ?>">
                    <div class="form-group">
                        <div>
                            <button class="btn btn-primary " name="submit" type="submit">
                                Submit
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    function deactivate() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
//                document.getElementById("demo").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "http://localhost:8080/Project/quizes/deactivate/1", true);
        xhttp.send(null);
        alert("Quiz deactivated Successfully");
    }
</script>