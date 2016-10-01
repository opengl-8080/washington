# ユースケース
## 候補日を登録する
- 範囲（開始日・終了日）を指定して候補日を一括で登録できる
- 指定範囲内に既に候補日が存在する場合はスキップする
    - 土日も自動的にスキップする
- １日だけを指定して登録することも可能

## 候補日を削除する
- 登録済みの候補日を削除することができる
- 範囲指定で削除可能

## 月を指定して候補日を確認する
- 月単位で候補日の状態を確認できる
- 候補日が登録されている日付は、以下の情報が確認できる
    - 最大参加人数
    - 参加者数が最大となる時間帯
- また、候補日のうち開催予定日となっているものについては、追加で以下のことが確認できる
    - 開催予定日であること
    - 開催回
    - 内容（先頭の一部のみ可）

## 候補日の詳細を確認する
- 日付を指定して候補日の詳細を確認できる
- 確認できる内容
    - 日付
    - 時間帯ごとの参加可能人数
    - 時間帯ごとのログインメンバーの参加可否
    - 開催予定日かどうか
    - 開催回
    - 内容
- また、ログインメンバーは時間帯ごとの参加可否を変更することもできる
- 管理者は以下のことも変更できる
    - 開催予定日
    - 開催回
    - 内容

# その他
## メンバー管理について
- 初回リリースでは機能を持たせない
- メンバーは固定なので、初回リリースでは不要と判断
- パスワード管理も特にしない
- ログインIDのみでアクセス可能
- 管理者はログインIDでベタ判定
