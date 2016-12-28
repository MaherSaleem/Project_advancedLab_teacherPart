<!-- Special version of Bootstrap that only affects content wrapped in .bootstrap-iso -->

<!-- Inline CSS based on choices in "Settings" tab -->
<head>
    <link rel="stylesheet" type="text/css" href="css_for_main_page.css">
    <style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form {
            font-family: Arial, Helvetica, sans-serif;
            color: black
        }

        .bootstrap-iso form button, .bootstrap-iso form button:hover {
            color: white !important;
        }

        .asteriskField {
            color: red;
        }

        .bootstrap-iso form .input-group-addon {
            color: #555555;
            background-color: #f7dddd;
            border-radius: 4px;
            padding-left: 12px
        }</style>
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css"/>

</head>

<ul class="nav nav-pills">
    <li class="active"><a href="#">Home</a></li>
    <li><a href="Add_student.php">new Stdent</a></li>
    <li><a href="Quiz.php">New Quiz</a></li>
    <li><a href="Grades.php">Grades</a></li>

</ul>
<div class="view" >
<div class="jumbotron" contenteditable="true">
    <h1>Add new Quiz</h1>

    <!-- HTML Form (wrapped in a .bootstrap-iso div) -->
    <div class="">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-xs-12">
                    <form method="post" action="http://localhost:8080/Project/quizes">
                        <div class="form-group ">
                            <label class="control-label requiredField" for="qid">
                                Quiz Id
       <span class="asteriskField">
        *
       </span>
                            </label>
                            <input class="form-control" id="qid" name="qid" type="text"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="s_time">
                                Start Time
       <span class="asteriskField">
        *
       </span>
                            </label>
                            <input class="form-control" id="s_time" name="s_time"
                                   type="datetime-local"/>
                        </div>
                        <div class="form-group ">
                            <label class="control-label requiredField" for="duration">
                                Duration
       <span class="asteriskField">
        *
       </span>
                            </label>
                            <input class="form-control" id="duration" name="duration" type="datetime-local"/>
                        </div>
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
</div>
</div>
<h1>Add new Question for particular Quiz</h1>

<?php
//http://stackoverflow.com/questions/15043981/how-to-access-json-decoded-array-in-php
$json = file_get_contents('http://localhost:8080/Project/quizes'); // this WILL do an http request for you
$data = json_decode($json, true);
for ($i = 0; $i < sizeof($data); $i++) {
    echo "<li><a href='add_question.php?qid=" . $data[$i]['qid'] . "'>quiz" . $data[$i]['qid'] . "</a></li><br>";
}
?>

