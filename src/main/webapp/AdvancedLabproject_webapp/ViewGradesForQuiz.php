
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
<?php
/**
 * Created by PhpStorm.
 * User: Maher
 * Date: 12/27/2016
 * Time: 3:08 PM
 */


$quizId = $_GET["qid"];
echo "<h1>Grades for Quiz" . $quizId . "</h1>";

$json = file_get_contents('http://localhost:8080/Project/grades/quiz/' . $quizId); // this WILL do an http request for you
$data = json_decode($json, true);
$sum = 0;
$max = -1;
$min = 100;
for ($i = 0; $i < sizeof($data); $i++) {
    echo "<h3>".$data[$i]["stu_id"] . " " . $data[$i]["stu_name"] . " "   . $data[$i]["grade"]*100 . "</h3>" ;
    $sum +=$data[$i]["grade"]*100;
    if($data[$i]["grade"]*100 > $max) $max = $data[$i]["grade"]*100;
    if($data[$i]["grade"]*100 < $min) $min = $data[$i]["grade"]*100;

}
echo "<br><br>";
echo "<h3>avg is " . $sum/sizeof($data) . "</h3>";
echo "<h3>max is $max </h3>";
echo "<h3>min is $min </h3>";



