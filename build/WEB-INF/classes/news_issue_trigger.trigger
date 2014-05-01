-- DROP TRIGGER issue_history; -- É¾³ý´¥·¢Æ÷


create trigger issue_history after update on news for each row
begin
  
IF NEW.issue!=OLD.issue then
  
  insert into news_issue_history (issue,id,title,modifier,modifydate)
  values (NEW.issue,NEW.id,NEW.title,NEW.modifier,NEW.modifydate);
  
END IF;
  
end;