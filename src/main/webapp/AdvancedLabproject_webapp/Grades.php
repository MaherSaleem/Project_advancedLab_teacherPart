<?php include_once "conntecting.php"?>

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
    <li ><a href="main_page.php">Home</a></li>
    <li><a href="Add_student.php">new Stdent</a></li>
    <li ><a href="Quiz.php">Quiz</a></li>
    <li class="active"><a href="Grades.php">Grades</a></li>

    </li>
</ul>
<div class="row">
  <div class="col-sm-6">
<div class="list-group">
<br>
<?php
/**
 * Created by PhpStorm.
 * User: Maher
 * Date: 12/27/2016
 * Time: 3:06 PM
 */


$json = file_get_contents('http://'.$IP.':8080/Project/quizes'); // this WILL do an http request for you
$data = json_decode($json, true);
for ($i = 0; $i < sizeof($data); $i++) {
    echo "<a class='list-group-item' href='ViewGradesForQuiz.php?qid=".$data[$i]['qid'] ."'>Quiz" . $data[$i]['qid'] . "</a>";
}
?>
</div>
</div>