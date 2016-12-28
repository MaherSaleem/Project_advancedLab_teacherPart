
<head>
    <link rel="stylesheet" type="text/css" href="css_for_main_page.css">
    <style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form{font-family: Arial, Helvetica, sans-serif; color: black}.bootstrap-iso form button, .bootstrap-iso form button:hover{color: white !important;} .asteriskField{color: red;}.bootstrap-iso form .input-group-addon {color:#555555; background-color: #f7dddd; border-radius: 4px; padding-left:12px}</style>
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css" />

</head>
<style>.bootstrap-iso .formden_header h2, .bootstrap-iso .formden_header p, .bootstrap-iso form {
        font-family: Arial, Helvetica, sans-serif;
        color: black
    }

    .bootstrap-iso form button, .bootstrap-iso form button:hover {
        color: white !important;
    }

    .asteriskField {
        color: red;
    }</style>

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
 * Time: 3:06 PM
 */

$json = file_get_contents('http://localhost:8080/Project/quizes'); // this WILL do an http request for you
$data = json_decode($json, true);
for ($i = 0; $i < sizeof($data); $i++) {
    echo "<li><a href='ViewGradesForQuiz.php?qid=".$data[$i]['qid'] ."'>quiz" . $data[$i]['qid'] . "</a></li><br>";
}
