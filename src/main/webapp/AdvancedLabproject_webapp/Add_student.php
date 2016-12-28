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
    <li><a href="Quiz.php">New Quiz</a></li>
    <li><a href="Grades.php">Grades</a></li>

    </li>
</ul>
<div class="view">
    <div class="jumbotron" contenteditable="true">

        <h1>Add new Student</h1>
        <!-- HTML Form (wrapped in a .bootstrap-iso div) -->
        <div class="">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <form method="post" action="http://localhost:8080/Project/students">
                            <div class="form-group ">
                                <label class="control-label requiredField" for="id">
                                    Student Id
       <span class="asteriskField">
        *
       </span>
                                </label>
                                <input class="form-control" id="id" name="id" type="text"/>
                            </div>
                            <div class="form-group ">
                                <label class="control-label requiredField" for="name">
                                    Student Name
       <span class="asteriskField">
        *
       </span>
                                </label>
                                <input class="form-control" id="name" name="name" type="text"/>
                            </div>
                            <div class="form-group ">
                                <label class="control-label requiredField" for="password">
                                    Password
       <span class="asteriskField">
        *


       </span>
                                </label>
                                <input class="form-control" id="password" name="password" type="text"/>
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