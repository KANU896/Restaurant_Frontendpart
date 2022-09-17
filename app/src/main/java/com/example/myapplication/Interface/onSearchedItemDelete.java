// 작성자 : 김도윤
// Searched_Adapter에서 삭제 버튼 클릭 시 User_Search_Page에서 인지할 수 있도록 Adapter와 User_Search_Page를 연결해주는 인터페이스
// Update : 22.08.18

package com.example.myapplication.Interface;

public interface onSearchedItemDelete {
    void onSearchedItemDelete(int position);
}
