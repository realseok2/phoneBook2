package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;

@WebServlet("/pbc") // 주소창에 들어 갈 이름
public class PhoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("/pbc --> doGet()"); 									// 중간 확인용 코드

//-----------------------------------------------------------------------------------------------

		String action = request.getParameter("action");

		// 리스트 출력 요청시--------------------------------------------------------------------------
		if ("list".equals(action)) { // list라는 문자열이 action의 값과 동일 할 경우
			System.out.println("list"); 											// 중간 확인용 코드

			PhoneDao phoneDao = new PhoneDao();
			List<PersonVo> pList = phoneDao.getPersonList();

			// 포워드 작업
			// 데이터를 request에 추가
			request.setAttribute("personList", pList); // perseonList가 pList를 앞으로 부를 이름, pList가 가져올 목록의 이름

			// forword 하는 방법 --> (./list.jsp)부분만 수정하여 사용하면 됩니다.
			WebUtil.forword(request, response, "/WEB-INF/list.jsp");
			/*
			 * RequestDispatcher rd = request.getRequestDispatcher("./WEB-INF/list.jsp");
			 * rd.forward(request, response); // request가 response를 앞으로 부를 이름, response가 가져갈
			 * 목록의 이름
			 */

			// 등록 요청시-------------------------------------------------------------------------------
		} else if ("wform".equals(action)) { // ----> 등록 폼 출력
			System.out.println("주소록 등록폼");

			// forword 하는 방법 --> (./list.jsp)부분만 수정하여 사용하면 됩니다.
			WebUtil.forword(request, response, "/WEB-INF/writeForm.jsp");

			/*
			 * RequestDispatcher rd =
			 * request.getRequestDispatcher("./WEB-INF/writeForm.jsp"); rd.forward(request,
			 * response); // request가 response를 앞으로 부를 이름, response가 가져갈 목록의 이름
			 */

		} else if ("insert".equals(action)) { // -----------------------------------------------> 등록
			System.out.println("번호 저장");
			
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");

			PersonVo vo = new PersonVo(name, hp, company);
			System.out.println(vo);
			PhoneDao phoneDao = new PhoneDao();
			phoneDao.personInsert(vo);

			// reDirect
			WebUtil.redirect(request, response, "/pb2/pbc?action=list");

			/*
			 * response.sendRedirect("/pb2/pbc?action=list"); // ----> 빈화면을 보내고 redirect로 다시
			 * 요청해서 불러옵니다.
			 */

		}

		// 등록 요청시

		//

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
