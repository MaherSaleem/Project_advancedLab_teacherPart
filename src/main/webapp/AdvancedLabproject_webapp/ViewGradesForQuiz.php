<?php include_once "conntecting.php"?>

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
    <li ><a href="main_page.php">Home</a></li>
    <li><a href="Add_student.php">new Stdent</a></li>
    <li><a href="Quiz.php">Quiz</a></li>
    <li class="active"><a href="Grades.php">Grades</a></li>

</ul>

<div class="container">
<?php
$quizId = $_GET["qid"];
echo "<h2>Grades for Quiz" . $quizId . "</h2>";
?>

<table class="table table-striped">
    <thead>
      <tr>
        <th>Student ID</th>
        <th>Name</th>
        <th>Grade</th>
      </tr>
     </thead>
    <tbody>

<?php

$json = file_get_contents('http://'.$IP.':8080/Project/grades/quiz/' . $quizId); // this WILL do an http request for you
$data = json_decode($json, true);
$sum = 0;
$max = -1;
$min = 100;

for ($i = 0; $i < sizeof($data); $i++) {
	echo "<tr>";
    echo "<td>".$data[$i]["stu_id"] . "</td> " ."<td>". $data[$i]["stu_name"] . "</td> "   . "<td>".$data[$i]["grade"]*100 . "</td>" ;
    $sum +=$data[$i]["grade"]*100;
    if($data[$i]["grade"]*100 > $max) $max = $data[$i]["grade"]*100;
    if($data[$i]["grade"]*100 < $min) $min = $data[$i]["grade"]*100;
    echo "</tr>";
}
?>
    </tbody>
  </table>

  <?php

echo "<br><br>";
echo "<table class='table table-striped'><thead><tr>";
echo "<td>Avg</td>";
echo "<td>max </td>";
echo "<td>min </td></tr></thead>";
echo "<tr><td> ". $sum/sizeof($data) ."</td><td>$max</td><td>$min</td></tr>";
echo "</table>";

?>
