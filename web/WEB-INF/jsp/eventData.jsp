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
        <td class="align-center"> 
            <fmt:parseNumber var = "gap1" type = "number" value = "${eventData.gap1}" />
            <c:if test="${gap1 > 0}">
                <span style="color: #26c281;">${eventData.gap1}%</span>
            </c:if>
            <c:if test="${gap1 <= 0}">
                <span style="color: #e35b5a;">${eventData.gap1}%</span> 
            </c:if>
        </td>
        <td class="align-center"> 
            <span style="font-size: 13px; border-bottom: 1px solid #000;">${eventData.price1}</span>
            <br/> 
            <span style="font-size: 13px;">${eventData.volume1}</span> 
        </td>
        <td class="align-center"> 
            <fmt:parseNumber var = "gap2" type = "number" value = "${eventData.gap2}" />
            <c:if test="${gap2 > 0}">
                <span style="color: #26c281;">${eventData.gap2}%</span>
            </c:if>
            <c:if test="${gap2 <= 0}">
                <span style="color: #e35b5a;">${eventData.gap2}%</span> 
            </c:if>    
        </td>
        <td class="align-center"> 
            <span style="font-size: 13px; border-bottom: 1px solid #000;">${eventData.price2}</span>
            <br/> 
            <span style="font-size: 13px;">${eventData.volume2}</span> 
        </td>
        <td class="align-center"> 
            <fmt:parseNumber var = "gap3" type = "number" value = "${eventData.gap3}" />
            <c:if test="${gap3 > 0}">
                <span style="color: #26c281;">${eventData.gap3}%</span>
            </c:if>
            <c:if test="${gap3 <= 0}">
                <span style="color: #e35b5a;">${eventData.gap3}%</span> 
            </c:if>
        </td>
        <td class="align-center"> 
            <span style="font-size: 13px; border-bottom: 1px solid #000;">${eventData.price3}</span>
            <br/> 
            <span style="font-size: 13px;">${eventData.volume3}</span>
        </td>
    </tr>
</c:forEach>