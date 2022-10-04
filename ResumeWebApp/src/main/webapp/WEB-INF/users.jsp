<%@ page import="com.company.entity.User" %>
<%@ page import="com.company.dao.impl.UserRepository" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Polad Sarukhanov
  Date: 30.05.2022
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <link rel="stylesheet" href="assets/css/users.css">
    <script src="assets/js/users.js"></script>
    <script src="https://kit.fontawesome.com/37d2329d90.js" crossorigin="anonymous"></script>
    <!-- CSS only(Bootstrap) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <!-- JS only(Bootstrap) -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
</head>
<body>
<%--<%--%>
<%--    User u =  (User)session.getAttribute("loggedInUser");--%>
<%--%>--%>
<%--<p>Welcome <%=u.getName()%>  <%=u.getSurname()%></p>--%>
<%
    List<User> users = (List<User>) request.getAttribute("users");
//
//    if (request.getParameter("search")!=null && request.getParameter("search").equals("Search")){
//        users = userDao.getAll(request.getParameter("name"),request.getParameter("surname"),null);
//    }
%>
<div class="container mycontainer">
    <div>
        <form action="${pageContext.request.contextPath}/WEB-INF/users.jsp" method="get">
            <div class="form-group">
                <label>name:</label>
                <input class="form-control" type="text" name="name" value="" placeholder="Enter name"/>
            </div>
            <br/>
            <div class="form-group">
                <label>surname:</label>
                <input class="form-control" type="text" name="surname" value="" placeholder="Enter surname"/>
            </div>
            <input class="btn btn-primary" type="submit" name="search" value="Search" style="margin: 7px"/>
        </form>
    </div>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Nationality</th>
                <th>operations</th>
            </tr>
            </thead>
            <tbody>
            <%for (User user : users) {%>
            <tr>
                <td><%=user.getName()%>
                </td>
                <td><%=user.getSurname()%>
                </td>
                <td><%=user.getNationality().getName() == null ? "N/A" : user.getNationality().getName()%>
                </td>
                <td style="width: 70px">
                    <%--                    <form action="userdetail" method="POST">--%>
                    <input type="hidden" name="id" value="<%=user.getId()%>">
                    <input type="hidden" name="action" value="delete"/>
                    <!-- Button trigger modal -->
                    <button type="submit" name="action" value="delete" class="btn btn-danger" data-toggle="modal"
                            data-target="#exampleModal" onclick="setIdForDelete('<%=user.getId()%>')">
<%--                        https://fontawesome.com/icons/trash-can?s=solid FontAwesome was used (trash can)--%>
                        <i class="fa-solid fa-trash-can"></i>
                    </button>
                    <%--                    </form>--%>
                </td>
                <td style="width: 70px">
                    <%--                <input type="submit" name="action" value="Delete" class="btn"/>--%>
                    <form action="userdetail" method="get">
                        <input type="hidden" name="id" value="<%=user.getId()%>">
                        <input type="hidden" name="action" value="update"/>
                        <button type="submit" name="action" value="update" class="btn btn-secondary">
                            <i class=" fa-regular fa-pen-to-square">
                            </i></button>
                    </form>
                    <%--                <input type="submit" name="action" value="Update" class="btn"/>--%>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <form action="userdetail" method="post">
                    <input type="hidden" name="id" value="" id="idForDelete">
                    <input type="hidden" name="action" value="delete">
                    <button type="submit" class="btn btn-danger" name="action" value="delete">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
