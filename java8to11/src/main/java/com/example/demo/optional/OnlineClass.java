package com.example.demo.optional;

import java.util.Optional;

public class OnlineClass {

    private int id;
    private String title;
    private boolean closed;
    private Progress progress;

    public OnlineClass(int id, String tilte, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    // 비어있는 값임을 감지하기 위하여 Optional으로 감싸서 사용할 수 있다.
    public Optional<Progress> getProgress(){
        // 뭔가 일이 제대로 진행되지 않을경우
        // 1. Exception 을 던진다.
//        if(this.progress == null){
//            throw new IllegalStateException();
//        }
        // 2. 클라이언트 코드에서 Exception을 처리한다.

        return Optional.ofNullable(progress);

        // null을 리턴하지 않도록 한다 -> 의미가 없어지는 거임
        // reutnr null;
        // return Optinal.empty(); // 진짜 리턴할꺼 없을때만 사용.
    }

    public void setProgress(Optional<Progress> progress) {
        progress.ifPresent((p) -> {
            this.progress = p;
        });
    }
}
