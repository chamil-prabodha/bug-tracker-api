INSERT INTO project(`name`, `project_key`) values ('Project 1', 'project-1');

INSERT INTO bug(`name`, `description`, `reported_by`, `status`, `severity`, `project_id`) values ('Bug 1', 'Description 1', 'USER 1', 'OPEN', 'LOW', 1);
INSERT INTO bug(`name`, `description`, `reported_by`, `status`, `severity`, `project_id`) values ('Bug 2', 'Description 2', 'USER 2', 'CLOSED', 'MEDIUM', 1);

INSERT INTO comment(`issue_id`, `content`, `author`) VALUES (1, 'Comment 1', 'USER 1');
INSERT INTO comment(`issue_id`, `content`, `author`) VALUES (1, 'Comment 2', 'USER 2');
INSERT INTO comment(`issue_id`, `content`, `author`) VALUES (2, 'Comment 1', 'USER 1');
INSERT INTO comment(`issue_id`, `content`, `author`) VALUES (2, 'Comment 2', 'USER 2');
