<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Include Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<div class="text-center col-md-6 mt-5" style="margin-left: 40%">
    <nav class="text-center" aria-label="Page navigation example">
        <ul class="pagination text-center" style="margin-left: 6%;">
            <c:choose>
                <c:when test="${sessionScope.page.getCurrentPage() > 1 && sessionScope.page.getCurrentPage() < sessionScope.page.getTotalPages()}">
                    <li class="page-item"><a class="page-link" href="${pagging}?cp=1&search=${paramSearch}">First</a></li>
                    <li class="page-item"><a class="page-link" href="${pagging}?cp=${sessionScope.page.getCurrentPage() - 1}&search=${paramSearch}">${sessionScope.page.getCurrentPage() - 1}</a></li>
                    <li class="page-item active"><a class="page-link" href="${pagging}?cp=${sessionScope.page.getCurrentPage()}&search=${paramSearch}">${sessionScope.page.getCurrentPage()}</a></li>
                    <li class="page-item"><a class="page-link" href="${pagging}?cp=${sessionScope.page.getCurrentPage() + 1}&search=${paramSearch}">${sessionScope.page.getCurrentPage() + 1}</a></li>
                    <li class="page-item"><a class="page-link" href="${pagging}?cp=${sessionScope.page.getTotalPages()}">Last</a></li>
                </c:when>
                <c:when test="${sessionScope.page.getCurrentPage() == 1}">
                    <li class="page-item active"><a class="page-link" href="${pagging}?cp=${sessionScope.page.getCurrentPage()}&search=${paramSearch}">${sessionScope.page.getCurrentPage()}</a></li>
                    <li class="page-item"><a class="page-link" href="${pagging}?cp=${sessionScope.page.getCurrentPage() + 1}&search=${paramSearch}">${sessionScope.page.getCurrentPage() + 1}</a></li>
                    <li class="page-item"><a class="page-link" href="${pagging}?cp=${sessionScope.page.getTotalPages()}">Last</a></li>
                </c:when>
                <c:when test="${sessionScope.page.getCurrentPage() == sessionScope.page.getTotalPages()}">
                    <li class="page-item"><a class="page-link" href="${pagging}?cp=1">First</a></li>
                    <li class="page-item"><a class="page-link" href="${pagging}?cp=${sessionScope.page.getCurrentPage() - 1}&search=${paramSearch}">${sessionScope.page.getCurrentPage() - 1}</a></li>
                    <li class="page-item active"><a class="page-link" href="${pagging}?cp=${sessionScope.page.getCurrentPage()}&search=${paramSearch}">${sessionScope.page.getCurrentPage()}</a></li>
                </c:when>
            </c:choose>
        </ul>
    </nav>
</div>
