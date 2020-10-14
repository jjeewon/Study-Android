package com.jiwon.flatversion;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RepositoryAdapter  {
    public final OnRepositoryItemClickListener onRepositoryItemClickListener;
    public final Context context;
    private List<GitHubService.RepositoryItem> items;

    public RepositoryAdapter(Context context, OnRepositoryItemClickListener onRepositoryItemClickListener){
        this.context = context;
        this.onRepositoryItemClickListener = onRepositoryItemClickListener;
    }

    /**
     * 리포지토리의 데이터를 설정해서 갱신한다.
     * @param items
     */
    public void setItemsAndRefresh(List<GitHubService.RepositoryItem> items){
        this.items = items;
        // notifyDataSetChanged();
    }

    public GitHubService.RepositoryItem getItemAt(int position){
        return items.get(position);
    }







    // 레포지토리의 아이템이 탭되면 호출됨
    interface OnRepositoryItemClickListener{
        void onRepositoryItemClick(GitHubService.RepositoryItem item);
    }

    /**
     * 뷰를 저장해 둘 클래스
     */
    static class RepoViewHolder extends RecyclerView.ViewHolder{
        private final TextView repoName;
        private final TextView repoDetail;
        private final ImageView repoImage;
        private final TextView starCount;

        public RepoViewHolder(View itemView){
            super(itemView);
            repoName = (TextView)itemView.findViewById(R.id.repo_name);
            repoDetail = (TextView)itemView.findViewById(R.id.repo_detail);
            repoImage = (ImageView) itemView.findViewById(R.id.repo_image);
            starCount = (TextView) itemView.findViewById(R.id.repo_star);
        }
    }
}
