<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:forEach var="eventData" items="${eventDataList}">
    <tr>
        <td class="align-center"> ${eventData.symbol} </td>
        <td class="align-center"> 
            <span style="font-size: 13px; border-bottom: 1px solid #000;">${eventData.price}</span>
            <br/> 
            <span style="font-size: 13px;">${eventData.volume}</span>
        </td>
        <c:forEach var="eventValue" items="${eventData.valueList}">
            <td class="align-center"> 
                <fmt:parseNumber var = "gap1" type = "number" value = "${eventValue.gap}" />
                <c:if test="${gap > 0}">
                    <span style="color: #26c281;">${eventValue.gap}%</span>
                </c:if>
                <c:if test="${gap <= 0}">
                    <span style="color: #e35b5a;">${eventValue.gap}%</span> 
                </c:if>
            </td>
            <td class="align-center"> 
                <span style="font-size: 13px; border-bottom: 1px solid #000;">${eventValue.price}</span>
                <br/> 
                <span style="font-size: 13px;">${eventValue.volume}</span> 
            </td>
        </c:forEach>
    </tr>
</c:forEach>